package com.tcl.friendserver.service;

import com.tcl.friendserver.dto.SupportCountDto;
import com.tcl.friendserver.model.FriendCommentSupport;
import com.tcl.friendserver.model.FriendWeiboSupport;

import java.util.List;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public interface RedisService {

    /**
     * 微博点赞, 状态为1
     * @param weiboId
     * @param supportUserId
     */
    void saveWeiboSupportToRedis(Long weiboId, Long supportUserId);

    /**
     * 取消微博点赞, 将状态改变为0
     * @param weiboId
     * @param supportUserId
     */
    void unWeiboSupportFromRedis(Long weiboId, Long supportUserId);

    /**
     * 从Redis中删除一条微博点赞数据
     * @param weiboId
     * @param supportUserId
     */
    void deleteWeiboSupportFromRedis(Long weiboId, Long supportUserId);

    /**
     * 评论点赞, 状态为1
     * @param commentId
     * @param supportUserId
     */
    void saveCommentSupportToRedis(Long commentId, Long supportUserId);

    /**
     * 取消评论点赞, 将状态改变为0
     * @param commentId
     * @param supportUserId
     */
    void unCommentSupportFromRedis(Long commentId, Long supportUserId);

    /**
     * 从Redis中删除一条评论点赞数据
     * @param commentId
     * @param supportUserId
     */
    void deleteCommentSupportFromRedis(Long commentId, Long supportUserId);

    /**
     * 该微博的点赞数量加1
     * @param weiboId
     */
    void incrementWeiboSupportCount(Long weiboId);

    /**
     * 该微博的点赞数量减1
     * @param weiboId
     */
    void decrementWeiboSupportCount(Long weiboId);

    /**
     * 该评论的点赞数量加1
     * @param commentId
     */
    void incrementCommentSupportCount(Long commentId);

    /**
     * 该评论的点赞数量减1
     * @param commentId
     */
    void decrementCommentSupportCount(Long commentId);

    /**
     * 获取Redis中存储的所有微博点赞数据
     * @return
     */
    List<FriendWeiboSupport> getAllWeiboSupportDataFromRedis();

    /**
     * 获取Redis中存储的该微博的点赞数量
     * @param weiboId
     * @return
     */
    Long getWeiboSupportCountFromRedis(Long weiboId);

    /**
     * 获取Redis中存储的所有微博的点赞数量
     * @return
     */
    List<SupportCountDto> getAllWeiboSupportCountFromRedis();

    /**
     * 获取Redis中存储的所有评论点赞数据
     * @return
     */
    List<FriendCommentSupport> getAllCommentSupportDataFromRedis();

    /**
     * 获取Redis中存储的该评论的点赞数量
     * @param commentId
     * @return
     */
    Long getCommentSupportCountFromRedis(Long commentId);

    /**
     * 获取Redis中存储的所有评论的点赞数量
     * @return
     */
    List<SupportCountDto> getAllCommentsupportCountFromRedis();

}
