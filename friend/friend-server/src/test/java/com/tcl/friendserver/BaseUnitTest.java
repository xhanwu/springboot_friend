package com.tcl.friendserver;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendServerApplication.class)
@AutoConfigureMockMvc
public class BaseUnitTest {

    protected String host = "http://localhost:8080";

    @Autowired
    protected MockMvc mvc;

    @Before
    public void init() throws Exception {
        System.out.println("初始化一些参数");
    }
}
