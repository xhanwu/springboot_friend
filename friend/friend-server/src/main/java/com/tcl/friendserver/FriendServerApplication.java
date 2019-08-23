package com.tcl.friendserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tcl.friendserver.mapper")
public class FriendServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendServerApplication.class, args);
    }

}
