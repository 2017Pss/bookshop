package com.daniel.service.impl;

import com.daniel.dao.BookCollectDAO;
import com.daniel.dao.BookDAO;
import com.daniel.dao.BookImageDAO;
import com.daniel.pojo.Book;
import com.daniel.pojo.BookCollect;
import com.daniel.service.BookCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookCollectServiceImpl implements BookCollectService {
    @Autowired
    private BookCollectDAO bookCollectDAO;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private BookImageDAO bookImageDAO;

    @Override
    public List<Book> getBookAndCollectBook(int startIndex,String id) {
        Map<Integer, List<Book>> collectMap = new LinkedHashMap<>();
        List<Book> collectList = new ArrayList<>();
        List<BookCollect> bookCollects = this.getListByStudentId((startIndex-1)*4,id);
        if (bookCollects.size()>0){
            for (BookCollect bookCollect: bookCollects){
                Book book = bookDAO.get(bookCollect.getBookId());
                book.setBookImage(bookImageDAO.getByBookId(book.getId()));
                book.setCollectPrice(bookCollect.getCollectPrice());
                collectList.add(book);
            }
            return collectList;
        }
        return null;
    }

    @Override
    public List<BookCollect> getBookByStudentId(String id) {
        List<BookCollect> bookCollects = bookCollectDAO.getBookByStudentId(id);
        return bookCollects;
    }

    @Override
    public void add(BookCollect bookcollect) {
        bookCollectDAO.add(bookcollect);
    }

    @Override
    public List<BookCollect> getListByStudentId(int startIndex,String id) {
        List<BookCollect> books = bookCollectDAO.getListByStudentId(startIndex,4,id);
        return books;
    }

    @Override
   public int getPageCount(String id){
        int collectBookNum = bookCollectDAO.getCollectBookNum(id);
        if (collectBookNum > 0){
            return (collectBookNum%4==0)?(collectBookNum/4):(collectBookNum/4+1);
        }else{
            return 1;
        }

    }
}
