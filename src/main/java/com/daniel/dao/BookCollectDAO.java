package com.daniel.dao;

import com.daniel.pojo.BookCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookCollectDAO {

    /**
     * 添加书
     * @param
     */
    void add(BookCollect bookcollect);

    /**
     * 根据条件查询所有图书
     * @param id StudentId
     * @return 对应的List
     */
    List<BookCollect> getBookByStudentId(@Param("id") String id);

}
