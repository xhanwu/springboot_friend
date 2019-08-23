package com.tcl.friendserver.controller;

import com.tcl.friendserver.dataTransfer.Result;
import com.tcl.friendserver.model.FriendWeibo;
import com.tcl.friendserver.model.FriendWeiboComment;
import com.tcl.friendserver.model.User;
import com.tcl.friendserver.service.WeiboCommentService;
import com.tcl.friendserver.service.WeiboService;
import com.tcl.friendserver.utils.WeiboCommentUtils;
import com.tcl.friendserver.utils.WeiboUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@RestController
@MapperScan("com.tcl.friendserver.mapper")
@RequestMapping("/api")
public class WeiboController {

    @Autowired
    private WeiboService weiboService;

    @Autowired
    private WeiboCommentService weiboCommentService;

    @PostMapping("/weibo")
    public Result createWeibo(@RequestParam Map<String,Object> params) {

        FriendWeibo friendWeibo = WeiboUtils.weiboFormat(params);

//        User userbytoken = userService.getUserByToken(token);
//        if (userbytoken == null) return QuarkResult.warn("用户不存在,请先登录！");
//
//        User user = userService.findOne(userbytoken.getId());
//        if (user.getEnable() != 1) return QuarkResult.warn("用户处于封禁状态！");

        if (weiboService.createWeibo(friendWeibo)) {
            return Result.success("发布/更新微博成功", null);
        } else {
            return Result.failure(Result.ERROR,"发布/更新微博失败");
        }
    }

    @PostMapping("/weibo/{weiboid}")
    public Result deleteWeibo(@PathVariable("weiboid") String weiboId) {
        if (StringUtils.isEmpty(weiboId)) {
            return Result.failure(Result.ERROR,"Invaild weiboId");
        }

        Long tempWeiboId = Long.valueOf(weiboId);
        Boolean result = weiboService.deleteWeibo(tempWeiboId);

        if (result) {
            return Result.success();
        } else {
            return Result.failure(Result.ERROR,"删除微博失败");
        }
    }

    @PostMapping("/weibosupport/{weiboid}")
    public Result weiboSupport (Long weiboId) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Long uid = user.getUid().longValue();

        Result data = Result.failure("操作失败");
        try {
            weiboService.weiboSupport(weiboId, uid);
            data = Result.success();
        } catch (Exception e) {
            data = Result.failure(e.getMessage());
        }
        return data;
    }

    @PostMapping("/repost")
    public void repostWeibo(Long weiboId) {
    }

    @PostMapping("/weibo_comment")
    public Result weiboComment(@RequestParam Map<String, Object> params) {
        FriendWeiboComment friendWeiboComment = WeiboCommentUtils.weiboCommentFormat(params);

        if (weiboCommentService.createWeiboComment(friendWeiboComment)) {
            return Result.success("发布/更新评论成功", null);
        } else {
            return Result.failure(Result.ERROR,"发布/更新评论失败");
        }
    }

    @PostMapping("/weibo_comment/{weiboCommentId}")
    public Result deleteWeiboComment(@PathVariable("weiboCommentId") String weiboCommentId) {
        if (StringUtils.isEmpty(weiboCommentId)) {
            return Result.failure(Result.ERROR,"Invaild weiboCommentId");
        }

        Long tempWeiboCommentId = Long.valueOf(weiboCommentId);
        Boolean result = weiboCommentService.deleteWeiboComment(tempWeiboCommentId);

        if (result) {
            return Result.success();
        } else {
            return Result.failure(Result.ERROR,"删除评论失败");
        }
    }

    @PostMapping("/commentsupport/{commentid}")
    public Result weiboCommentSupport (Long commentId) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Long uid = user.getUid().longValue();

        Result data = Result.failure("操作失败");
        try {
            weiboCommentService.commentSupport(commentId, uid);
            data = Result.success();
        } catch (Exception e) {
            data = Result.failure(e.getMessage());
        }
        return data;
    }
}
