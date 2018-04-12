package com.qwj.mychat.controller;

import com.qwj.mychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public Object register(String phone, String username, String password){
        String message;
        System.out.print(username);
        if (userService.selectByPhone(phone) != null){
            message = "该帐号已注册！";
        }else {
            userService.insertUser(phone, username, password);
            message = "注册成功！";
        }
        return message;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(String phone, String password, HttpServletRequest request){
        String message = null;
        HttpSession httpSession = request.getSession();
        if (userService.selectByPhoneAndPwd(phone, password) != null){
            httpSession.setAttribute("user", phone);
            System.out.print(httpSession.getAttribute("user"));
            message = "登录成功!";
        }else if (userService.selectByPhone(phone) == null){
            message = "用户不存在！";
        }else if (!password.equals(userService.selectPwdByPhone(phone))){
            message = "密码错误！";
        }
        return message;
    }

}
