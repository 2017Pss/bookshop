package com.daniel.service;

import com.daniel.pojo.Book;
import com.daniel.pojo.BookCollect;

import java.util.List;
import java.util.Map;

public interface BookCollectService {
    /**
     * 添加图书收藏
     * @param
     */
    void add(BookCollect bookcollect);

    List<BookCollect> getBookByStudentId(String id);

    Map<Integer, List<Book>> getBookAndCollectBook(String id);
}
