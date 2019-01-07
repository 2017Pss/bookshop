package com.daniel.service;

import com.daniel.pojo.BookCollect;

import java.util.List;

public interface BookCollectService {
    /**
     * 添加图书收藏
     * @param
     */
    void add(BookCollect bookcollect);

    List<BookCollect> getBookByStudentId(String id);
}
