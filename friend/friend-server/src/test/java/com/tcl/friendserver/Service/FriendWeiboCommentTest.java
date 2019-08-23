package com.tcl.friendserver.Service;

import com.tcl.friendserver.BaseUnitTest;
import com.tcl.friendserver.FriendServerApplication;
import com.tcl.friendserver.model.FriendWeiboComment;
import com.tcl.friendserver.service.WeiboCommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendServerApplication.class)
@AutoConfigureMockMvc
public class FriendWeiboCommentTest extends BaseUnitTest {

    @Autowired
    private WeiboCommentService weiboCommentService;

    @Test
    public void createWeiboCommentTest() {
        for (int i = 1; i < 5; i++) {
            FriendWeiboComment friendWeiboComment = new FriendWeiboComment();
            friendWeiboComment.setWeiboId(2l);
            friendWeiboComment.setUserId(10L);
            friendWeiboComment.setContent("微博评论11111_" + i);
            friendWeiboComment.setSupportCount(100+i);
            friendWeiboComment.setCreateTime(new Date());
            weiboCommentService.createWeiboComment(friendWeiboComment);
        }

        for (int j = 1; j < 5; j++) {
            FriendWeiboComment friendWeiboComment = new FriendWeiboComment();
            friendWeiboComment.setWeiboId(5l);
            friendWeiboComment.setUserId(20L);
            friendWeiboComment.setContent("微博评论222_" + j);
            friendWeiboComment.setSupportCount(100+j);
            friendWeiboComment.setCreateTime(new Date());
            weiboCommentService.createWeiboComment(friendWeiboComment);
        }
    }

    @Test
    public void updateWeiboCommentTest() throws InterruptedException {
        Long weiboCommentId1 = 2l;
        FriendWeiboComment friendWeiboComment = new FriendWeiboComment();
        friendWeiboComment.setId(weiboCommentId1);
        friendWeiboComment.setContent("更新weiboCommentId为2的微博");
        weiboCommentService.createWeiboComment(friendWeiboComment);

        Long weiboCommentId2 = 7l;
        FriendWeiboComment friendWeiboComment1 = new FriendWeiboComment();
        friendWeiboComment1.setId(weiboCommentId2);
        friendWeiboComment1.setContent("更新weiboCommentId为7的微博");
        weiboCommentService.createWeiboComment(friendWeiboComment1);
    }

    @Test
    public void deleteWeiboCommentTest() throws InterruptedException {
        Long weiboCommentId1 = 3l;
        weiboCommentService.deleteWeiboComment(weiboCommentId1);

        Long weiboCommentId2 = 6l;
        weiboCommentService.deleteWeiboComment(weiboCommentId2);
    }

    @Test
    public void weiboSupportTest() throws InterruptedException {

        Long weiboCommentId1 = 1l;
        Long supportUserId1 = 10l;
        weiboCommentService.commentSupport(weiboCommentId1, supportUserId1);

        Long weiboCommentId2 = 5l;
        Long supportUserId2 = 20l;
        weiboCommentService.commentSupport(weiboCommentId2, supportUserId2);
    }
}
