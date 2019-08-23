package com.tcl.friendserver.utils;

import com.tcl.friendserver.model.FriendWeibo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class WeiboUtils {

    public static FriendWeibo weiboFormat(Map<String,Object> params) {
        FriendWeibo friendWeibo = new FriendWeibo();
        Long id = (Long) params.get("id");
        Long uid = (Long) params.get("uid");
        String content = (String) params.get("content");
        Date createTime = (Date) params.get("create_time");
        Boolean status = (Boolean) params.get("status");
        Boolean isTop = (Boolean) params.get("is_top");
        Boolean isComment = (Boolean) params.get("is_comment");
        Integer supportCount = (Integer) params.get("support_count");
        String weiboType = (String) params.get("weibo_type");
        Long crowdId = (Long) params.get("crowd_id");
        Integer repostCount = (Integer) params.get("repost_count");
        String fromDevice = (String) params.get("from_device");

        friendWeibo.setId(id);
        friendWeibo.setUid(uid);
        friendWeibo.setContent(content);
        friendWeibo.setCreateTime(createTime);
        friendWeibo.setStatus(status);
        friendWeibo.setIsTop(isTop);
        friendWeibo.setIsComment(isComment);
        friendWeibo.setSupportCount(supportCount);
        friendWeibo.setWeiboType(weiboType);
        friendWeibo.setCrowdId(crowdId);
        friendWeibo.setRepostCount(repostCount);
        friendWeibo.setFromDevice(fromDevice);

        log.info("get details weibo information = " + friendWeibo.toString());
        return friendWeibo;
    }
}
