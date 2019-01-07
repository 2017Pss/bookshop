package com.daniel.service.impl;

import com.daniel.dao.BookCollectDAO;
import com.daniel.pojo.BookCollect;
import com.daniel.service.BookCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCollectServiceImpl implements BookCollectService {
    @Autowired
    private BookCollectDAO bookCollectDAO;

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
