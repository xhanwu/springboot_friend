package com.tcl.friendserver.controller;


import com.tcl.friendserver.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
public class TestController {

    @RequestMapping("/")
    public String hi() {
        return "Congratulations! It works!!!!!!!!!";
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getdb")
    public String select() {
        return userMapper.selectByPrimaryKey(1).toString();
    }

    @RequestMapping("/log")
    public String log() {
        BufferedReader bufferedReader = null;
        StringBuffer sb = new StringBuffer();
        try {
            String property = System.getProperty("user.dir");
            bufferedReader = new BufferedReader(new FileReader(property + "/main.log"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}