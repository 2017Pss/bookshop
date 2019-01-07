package com.daniel.service.impl;

import com.daniel.dao.BookCollectDAO;
import com.daniel.dao.BookDAO;
import com.daniel.dao.BookImageDAO;
import com.daniel.dao.CategoryDAO;
import com.daniel.pojo.Book;
import com.daniel.pojo.BookCollect;
import com.daniel.pojo.Category;
import com.daniel.service.BookCollectService;
import com.daniel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private BookImageDAO bookImageDAO;
    @Autowired
    private BookCollectService bookCollectService;

    @Override
    public Map<Category,List<Book>> listByCategory(String studentId) {

        //TODO 查询当前用户所有书籍收藏信息
        List<BookCollect> bookCollects = bookCollectService.getBookByStudentId(studentId);


        // 获取所有Category
        List<Category> categories = categoryDAO.list();
        // 使用LinkedHashMap存储，若使用HashMap则无序
        Map<Category,List<Book>> booksMap = new LinkedHashMap<>();
        for (Category category : categories) {
            List<Book> books = bookDAO.getListByCategoryId(0,5,1,category.getId());
            if (books.size() > 0) {
                // 当前的Book对象无BookImage，遍历每个Book对象并放入相应的BookImage
                for (Book book : books) {
                    book.setBookImage(bookImageDAO.getByBookId(book.getId()));
                    for (BookCollect bookCollect : bookCollects){
                        if (bookCollect.getBookId() == book.getId()){
                            book.setCollectId(1);
                        }
                    }
                }
            }
            booksMap.put(category,books);
        }
        return booksMap;
    }

    @Override
    public List<Book> listByUserId(int uid,int bookType) {
        List<Book> books = bookDAO.getListByUserId(uid,bookType);
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public List<Book> listByCategoryId(int bookType,int cid) {
        List<Book> books = bookDAO.getListByCategoryId(-1,-1,bookType,cid);
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public Book get(int id) {
         Book book = bookDAO.get(id);
         book.setBookImage(bookImageDAO.getByBookId(id));
         return book;
    }

    @Override
    public int getUserId(int id) {
        return bookDAO.getUserId(id);
    }

    @Override
    public void add(Book book) {
        bookDAO.add(book);
    }

    @Override
    public int count() {
        return bookDAO.count();
    }

    @Override
    public void delete(int id) {
        bookDAO.delete(id);
    }

    @Override
    public List<Book> list() {
        List<Book> books = bookDAO.list();
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public List<Book> listByBookType(int bookType) {
        List<Book> books = bookDAO.listByBookType(bookType);
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

}
