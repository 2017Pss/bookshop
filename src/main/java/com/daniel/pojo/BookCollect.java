package com.daniel.pojo;

import java.util.Date;

public class BookCollect {
    private int id;
    private int bookId;
    private  String studentId;
    private Date createTime;
    private double collectPrice;

    public double getCollectPrice() {
        return collectPrice;
    }

    public void setCollectPrice(double collectPrice) {
        this.collectPrice = collectPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
