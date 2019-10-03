package com.qfedu.service.impl;

import com.qfedu.entry.Admin;
import com.qfedu.mapper.AdminMapper;
import com.qfedu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean isLogin(String username, String password) {

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        int result = adminMapper.selectAdminByUserNameAndPassword(admin);

        return result > 0 ? true : false;
    }
}
