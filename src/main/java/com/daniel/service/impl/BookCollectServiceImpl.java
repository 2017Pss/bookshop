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
    public Map<Integer, List<Book>> getBookAndCollectBook(String id) {
        Map<Integer, List<Book>> collectMap = new LinkedHashMap<>();
        List<Book> collectList = new ArrayList<>();
        List<BookCollect> bookCollects = this.getBookByStudentId(id);
        if (bookCollects.size()>0){
            for (BookCollect bookCollect: bookCollects){
                Book book = bookDAO.get(bookCollect.getBookId());
                book.setBookImage(bookImageDAO.getByBookId(book.getId()));
                collectList.add(book);
            }
        }
        int size = (collectList.size()%4==0)?(collectList.size()/4):(collectList.size()/4+1);
        for(int i=1; i<=size; i++){
            List<Book> bookList = new ArrayList<>();
            for (int j=1; j<=4; j++){
                bookList.add(collectList.get(4*(i-1)+j-1));
            }
            collectMap.put(i,bookList);
        }
        return collectMap;
    }

    @Override
    public void add(BookCollect bookcollect) {
        bookCollectDAO.add(bookcollect);
    }

    @Override
    public List<BookCollect> getBookByStudentId(String id) {
        List<BookCollect> bookCollects = bookCollectDAO.getBookByStudentId(id);
        return bookCollects;
    }
}
