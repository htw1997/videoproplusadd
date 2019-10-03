package com.qfedu.controller;

import com.qfedu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    //从spring容器中获取一个AdminService的实现类赋值给该变量
    @RequestMapping("/showLogin")
    public String showLogin(){
        return "behind/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpSession session){

        if (adminService.isLogin(username,password)){
            session.setAttribute("USERNAME",username);
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/exit")
    public String login( HttpSession session) {
        session.removeAttribute("userName");
        return "behind/login";
    }
}
