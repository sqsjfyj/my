package com.qwj.mychat.mapper;

import com.qwj.mychat.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Insert("insert into user(phone,username,password) values(#{phone},#{username},#{password})")
    int insertUser(User user);

    @Select("select * from user where phone=#{phone}")
    User selectByPhone(@Param("phone") String phone);

    @Select("select * from user where phone=#{phone} and password=#{password}")
    User selectByPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

    @Update("update user set username=#{username} and password=#{password} where phone=#{phone}")
    int updateUser(@Param("phone") String phone, @Param("username") String username, @Param("password") String password);

    @Select("select password from user where phone=#{phone}")
    String selectPwdByPhone(String phone);

}
