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
    public Map<Integer, List<Book>> getBookAndCollectBook(int startIndex,String id) {
        Map<Integer, List<Book>> collectMap = new LinkedHashMap<>();
        List<Book> collectList = new ArrayList<>();
        List<BookCollect> bookCollects = this.getListByStudentId((startIndex-1)*4,id);
        if (bookCollects.size()>0){
            for (BookCollect bookCollect: bookCollects){
                Book book = bookDAO.get(bookCollect.getBookId());
                book.setBookImage(bookImageDAO.getByBookId(book.getId()));
                collectList.add(book);
            }
        }
        int pageSize = bookCollects.size();
        collectMap.put(pageSize,collectList);
        return collectMap;
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
   public List<Integer> getPageCount(String id) {
        List<BookCollect> bookCollects = bookCollectDAO.getBookByStudentId(id);
        int count = (bookCollects.size()%4==0)?(bookCollects.size()/4):(bookCollects.size()/4+1);
        List<Integer> list = new ArrayList<>();
        if (count>0){
            for (int i=1;i<=count; i++){
                list.add(i);
            }
        }
        return list;
    }
}
