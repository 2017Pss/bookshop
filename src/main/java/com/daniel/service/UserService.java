package com.daniel.service;

import com.daniel.pojo.User;

public interface UserService {

    boolean checkUser(User user);
    User get(int id);
    User getByStudentId(String studnetid);
    void updateUserInfo(User user);
}
