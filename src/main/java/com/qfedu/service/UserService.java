package com.qfedu.service;

import com.qfedu.entry.User;

import java.util.List;

public interface UserService {
    int register(User user);

    int loginUser(User user);

    User getUserByEmail(String email);


    int updateUser(User user);


}
