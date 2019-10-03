package com.qfedu.mapper;

import com.qfedu.entry.User;

import java.util.List;

public interface UserMapper {

    int insertUser(User user);

    int getCountUser(User user);

    User getUserByEmail(String email);



    int updateBySelective(User user);



}
