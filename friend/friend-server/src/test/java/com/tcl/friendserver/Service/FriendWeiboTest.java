package com.tcl.friendserver.Service;

import com.tcl.friendserver.BaseUnitTest;
import com.tcl.friendserver.FriendServerApplication;
import com.tcl.friendserver.controller.WeiboController;
import com.tcl.friendserver.model.FriendWeibo;
import com.tcl.friendserver.service.WeiboService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendServerApplication.class)
@AutoConfigureMockMvc
public class FriendWeiboTest extends BaseUnitTest {

    @Autowired
    private WeiboService weiboService;

    @Autowired
    private WeiboController weiboController;

    @Test
    public void createWeiboTest() throws InterruptedException {
        for (int i = 1; i < 5; i++) {
            FriendWeibo friendWeibo = new FriendWeibo();
            friendWeibo.setUid(1l);
            friendWeibo.setContent("发微博测试111_" + i);
            friendWeibo.setSupportCount(10+i);
            friendWeibo.setIsTop(false);
            friendWeibo.setIsComment(true);
            friendWeibo.setCreateTime(new Date());
            friendWeibo.setFromDevice("TCL_" + i);
            friendWeibo.setWeiboType("Image111_" + i);
            friendWeibo.setRepostCount(5+i);
            weiboService.createWeibo(friendWeibo);
        }

        for (int j = 1; j < 5; j++) {
            FriendWeibo friendWeibo = new FriendWeibo();
            friendWeibo.setUid(2l);
            friendWeibo.setContent("发微博测试222_" + j);
            friendWeibo.setSupportCount(10+j);
            friendWeibo.setIsTop(false);
            friendWeibo.setIsComment(true);
            friendWeibo.setCreateTime(new Date());
            friendWeibo.setFromDevice("ALCATEL_" + j);
            friendWeibo.setWeiboType("Image222_" + j);
            friendWeibo.setRepostCount(5+j);
            weiboService.createWeibo(friendWeibo);
        }
//        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void updateWeibotest() throws InterruptedException {
        Long weiboId = 2l;
        Long uid = 1l;
        FriendWeibo friendWeibo = new FriendWeibo();
        friendWeibo.setId(weiboId);
        friendWeibo.setUid(uid);
        friendWeibo.setContent("更新weiboId为2, uid为1的微博");
        weiboService.createWeibo(friendWeibo);
    }

    @Test
    public void deltedWeiboTest1() throws InterruptedException {
        weiboController.deleteWeibo("2");
    }

    @Test
    public void deleteWeiboTest() throws InterruptedException {
        Long weiboId1 = 1l;
        weiboService.deleteWeibo(weiboId1);

        Long weiboId2 = 6l;
        weiboService.deleteWeibo(weiboId2);
    }

    @Test
    public void weiboSupportTest() throws InterruptedException {

        Long weiboId1 = 3l;
        Long supportUserId1 = 1l;
        weiboService.weiboSupport(weiboId1, supportUserId1);

        Long weiboId2 = 8l;
        Long supportUserId2 = 2l;
        weiboService.weiboSupport(weiboId2, supportUserId2);
    }
}
