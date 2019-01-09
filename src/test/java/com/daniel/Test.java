package com.daniel;

import com.daniel.pojo.BookCollect;

import java.util.Map;

public class Test {

    /*private static Student student;

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        this.student = student;
    }*/

    public static void main(String[] args){
        Student student1 = new Student();
        Student student2 = new Student();
        student1.setName("你好");
        student2.setName("他好");
        student1.setId(1);
        student2.setId(2);
        System.out.println(student1.getName() + "  " + student1.getId()); // 他好  1
        System.out.println(student2.getName() + "  " + student2.getId()); // 他好  2
        System.out.println(Student.name);
    }
}
