package com.tcl.friendserver.service;

import com.tcl.friendserver.model.FriendWeiboComment;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public interface WeiboCommentService {

    /**
     * 发表微博评论
     *
     * @param friendWeiboComment
     * @return
     */
    boolean createWeiboComment(FriendWeiboComment friendWeiboComment);

    /**
     * 删除微博评论
     *
     * @param commentiId
     * @return
     */
    boolean deleteWeiboComment(Long commentiId);

    /**
     * 评论点赞
     *
     * @param commentId
     * @param supportUserId
     * @return
     */
    void commentSupport(Long commentId, Long supportUserId);

    /**
     * 取消评论点赞
     *
     * @param commentId
     * @param supportUserId
     * @return
     */
    void unCommentSupport(Long commentId, Long supportUserId);

    /**
     * 删除一条评论点赞数据
     *
     * @param commentId
     * @param supportUserId
     * @return
     */
    void deleteCommentSupport(Long commentId, Long supportUserId);

    /**
     * 通过Id查评论
     *
     * @param commentId
     * @return
     */
    FriendWeiboComment findById(Long commentId);
}
