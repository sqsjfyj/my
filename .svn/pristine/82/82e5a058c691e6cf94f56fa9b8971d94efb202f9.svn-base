package com.qwj.mychat.service;

import com.qwj.mychat.entity.User;
import com.qwj.mychat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public int insertUser(String phone, String username, String password){
        User user = new User();
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.insertUser(user);
    }

    public User selectByPhone(String phone){
        return userMapper.selectByPhone(phone);
    }

    public User selectByPhoneAndPwd(String phone, String password){
        return userMapper.selectByPhoneAndPwd(phone, password);
    }

    public String selectPwdByPhone(String phone){
        return userMapper.selectPwdByPhone(phone);
    }

}
