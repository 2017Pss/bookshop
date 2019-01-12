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
     * 根据条件查询所有收藏图书
     * @param id StudentId
     * @return 对应的List
     */
    List<BookCollect> getBookByStudentId(@Param("id") String id);

    /**
     *
     * @param start 起始收藏图书ID
     * @param count 需要图书的总数
     * @param id studentId
     * @return
     */
    List<BookCollect> getListByStudentId(@Param("start") int start, @Param("count") int count, @Param("id") String id);

    /**
     * 查寻所收藏book的总数量
     * @param id studentId
     * @return 总数量
     */
    int getCollectBookNum(@Param("id") String id);

    /**
     * 删除收藏图书
     * @param bookCollect
     */
    void delete(int id);
}
