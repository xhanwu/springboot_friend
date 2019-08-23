package com.tcl.friendserver.utils;

import com.tcl.friendserver.model.FriendWeiboComment;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class WeiboCommentUtils {

    public static FriendWeiboComment weiboCommentFormat(Map<String,Object> params) {
        FriendWeiboComment friendWeiboComment = new FriendWeiboComment();
        Long id = (Long) params.get("id");
        Long weiboId = (Long) params.get("weibo_id");
        Long userId = (Long) params.get("user_id");
        String content = (String) params.get("content");
        Date createTime = (Date) params.get("create_time");
        Integer supportCount = (Integer) params.get("support_count");

        friendWeiboComment.setId(id);
        friendWeiboComment.setWeiboId(weiboId);
        friendWeiboComment.setUserId(userId);
        friendWeiboComment.setContent(content);
        friendWeiboComment.setCreateTime(createTime);
        friendWeiboComment.setSupportCount(supportCount);

        log.info("get details weibo information = " + friendWeiboComment.toString());
        return friendWeiboComment;
    }
}
