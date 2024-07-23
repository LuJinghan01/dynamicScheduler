package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.example.demo.bean.MyUser;

@MapperScan
public interface UserMapper {
    @Select("select * from user where user_name = #{userName}")
    @Result(column = "user_name", property = "username")
    @Result(column = "password", property = "password")
    MyUser findByUserName(String userName);

    @Insert("insert into user (user_name, password) values (#{user_name},#{password})")
    void insert(String user_name, String password);

    
    
}
