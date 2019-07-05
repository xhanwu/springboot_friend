package com.tcl.friendserver.controller;


import com.tcl.friendserver.mapper.UserMapper;
import com.tcl.friendserver.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan("com.tcl")
public class TestController {

    @RequestMapping("/")
    public String hi(){
        return "Congratulations! It works!";
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getdb")
    public User select(){
        return userMapper.selectByPrimaryKey(1);
    }
}