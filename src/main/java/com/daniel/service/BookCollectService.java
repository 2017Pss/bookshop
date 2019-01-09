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

    int getPageCount(String id);

    List<Book> getBookAndCollectBook(int startIndex,String id);

    List<BookCollect> getBookByStudentId(String id);

    /**
     * 返回分页所需的BookCollect集合
     * @param startIndex 起始收藏图书ID
     * @param id studentId
     * @return
     */
    List<BookCollect> getListByStudentId(int startIndex,String id);
}
