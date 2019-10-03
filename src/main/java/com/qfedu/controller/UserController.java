package com.qfedu.controller;

import com.qfedu.entry.User;
import com.qfedu.service.UserService;

import com.qfedu.utils.ImageCut;
import com.qfedu.utils.InfoUtils;
import com.qfedu.utils.Md5Utils;
import com.qfedu.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
@Api(value = "用户操作", tags = "用户操作接口，比如登录，注册等")

public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
    @ApiOperation(value = "接口名", notes = "接口描述", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "string")
    })
    public String validateEmail(String email) {
        //userService.jianyanEmail(email);

        //此处调用一个Service,校验邮箱是否可用   (自己写）
        return "success";
    }

    @RequestMapping(value = "/insertUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "注册用户", notes = "注册新用户", httpMethod = "GET")

    public String insertUser(User user, HttpSession session) {
        //调用service,完成user的添加
        user.setPassword(Md5Utils.getMd5Str(user.getPassword()));
        int result = userService.register(user);
        System.out.println(result);
        if (result > 0) {
            session.setAttribute("userAccount", user.getEmail());
            return "success";
        }
        return "fail";
    }


    @RequestMapping("/loginUser")
    @ResponseBody
    public String loginUser(User user, HttpSession session) {
        System.out.println(user);
        user.setPassword(Md5Utils.getMd5Str(user.getPassword()));
        int result = userService.loginUser(user);
        System.out.println(result);
        if (result > 0) {
            session.setAttribute("userAccount", user.getEmail());
            return "success";
        }
        return "fail";
    }


    @RequestMapping("/showMyProfile")
    public String showMyProfile(HttpSession session, Model model) {
        //展示个人中心

        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        user.setImgurl(InfoUtils.getProperties("IMG_URL") + user.getImgurl());
        return "before/my_profile";
    }


    @RequestMapping("/updateUser")

    public String updateUser(HttpSession session, Model model, User user) {
        //修改用户信息

        user.setEmail((String) session.getAttribute("userAccount"));
        //取出email，将email放入user里面，进行查询修改。
        int count = userService.updateUser(user);
        System.out.println(count);
        model.addAttribute("user", count);
        return "redirect:/user/showMyProfile";
    }


    @RequestMapping("/changeProfile")

    public String changeProfile(HttpSession session, Model model) {
        //修改个人中心
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgurl(InfoUtils.getProperties("IMG_URL") + user.getImgurl());
        model.addAttribute("user", user);

        return "before/change_profile";

    }

    @RequestMapping("/changeAvatar")
    public String changeAvatar(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgurl(InfoUtils.getProperties("IMG_URL") + user.getImgurl());
        model.addAttribute("user", user);
        return "before/change_avatar";
    }

    @RequestMapping("/passwordSafe")
    public String passwordSafe(HttpSession session, Model model) {
        return "before/password_safe";
    }
    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String newPassword,HttpSession session) {

        String email = (String) session.getAttribute("userAccount");
        User user = new User();
        user.setEmail(email);
        user.setPassword(Md5Utils.getMd5Str(newPassword));
        int rows = userService.updateUser(user);
        System.out.println(rows);
        if (rows > 0) {
            return "success";
        }
        return "fail";
    }


    @RequestMapping("/upLoadImage")
    public String uploadImg(MultipartFile image_file, HttpSession session, String x1, String x2, String y1, String y2) {

        /**
         * 1、对图片名称进行重命名
         * 2、获取图片的上传路径和图片服务器路径
         */
        String oldName = image_file.getOriginalFilename();
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        String newFileName = UUIDUtils.getUUID() + suffix;
        String fileDir = InfoUtils.getProperties("IMG_PATH");

        File file = new File(fileDir, newFileName);
        try {
            image_file.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(x1);
        System.out.println(x2);
        System.out.println(y1);
        System.out.println(y2);
        float _x1 = Float.valueOf(x1);
        float _x2 = Float.valueOf(x2);
        float _y1 = Float.valueOf(y1);
        float _y2 = Float.valueOf(y2);


        //在此处截取上传过的图片
        ImageCut imageCut = new ImageCut();

        imageCut.cutImage(fileDir + "\\" + newFileName, (int) _x1, (int) _y1, (int) (_x2 - _x1), (int) (_y2 - _y1));

        String mail = (String) session.getAttribute("userAccount");
        //更新数据库
        User user = new User();
        user.setEmail(mail);
        user.setImgurl(newFileName);
        int result = userService.updateUser(user);
        return "redirect:/user/showMyProfile";
    }
    @RequestMapping("/validatePassword")
    @ResponseBody
    public String validatePassword(String password,HttpSession session) {
        String email = (String) session.getAttribute("userAccount");
        User user = new User();
        user.setEmail(email);
        user.setPassword(Md5Utils.getMd5Str(password));
        Integer integer = userService.loginUser(user);
        if (integer > 0) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword() {
        return "before/forget_password";
    }
}