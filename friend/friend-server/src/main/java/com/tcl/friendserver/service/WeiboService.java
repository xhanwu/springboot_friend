package com.tcl.friendserver.service;

import com.tcl.friendserver.model.FriendWeibo;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public interface WeiboService {
    /**
     * 发微博
     *
     * @param friendWeibo
     * @return
     */
    boolean createWeibo(FriendWeibo friendWeibo);

    /**
     * 删微博
     *
     * @param weiboiId
     * @return
     */
    boolean deleteWeibo(Long weiboiId);

    /**
     * 转发微博
     *
     * @param friendWeibo
     * @return
     */
    boolean repostWeibo(FriendWeibo friendWeibo);

    /**
     * 微博点赞
     *
     * @param weiboId
     * @param supportUserId
     * @return
     */
    void weiboSupport(Long weiboId, Long supportUserId);

    /**
     * 取消微博点赞
     *
     * @param weiboId
     * @param supportUserId
     * @return
     */
    void unWeiboSupport(Long weiboId, Long supportUserId);

    /**
     * 删除一条微博点赞数据
     *
     * @param weiboId
     * @param supportUserId
     * @return
     */
    void deleteWeiboSupport(Long weiboId, Long supportUserId);

    /**
     * 通过微博Id获取一条微博数据
     *
     * @param weiboId
     * @return
     */
    FriendWeibo findById(Long weiboId);

}
