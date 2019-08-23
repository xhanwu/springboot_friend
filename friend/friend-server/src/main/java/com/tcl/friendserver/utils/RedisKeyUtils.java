package com.tcl.friendserver.utils;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public class RedisKeyUtils {

    //保存微博点赞状态数据的key
    public static final String KEY_WEIBO_SUPPORT_STATUS = "WEIBO_SUPPORT_STATUS";

    //保存评论点赞状态数据的key
    public static final String KEY_COMMENT_SUPPORT_STATUS = "COMMENT_SUPPORT_STATUS";

    //保存微博的用户被点赞数量的key
    public static final String KEY_WEIBO_SUPPORT_COUNT = "WEIBO_SUPPORT_COUNT";

    //保存评论的用户被点赞数量的key
    public static final String KEY_COMMENT_SUPPORT_COUNT = "COMMENT_SUPPORT_COUNT";

    /**
     * 拼接被点赞的微博id或评论id和点赞的人的id作为key。格式 11111:22222:0
     *
     * @param supportId 被点赞的微博或评论id
     * @param supportUserId 点赞的人的id
     * @return
     */
    public static String getSupportKey(Long supportId, Long supportUserId) {
        StringBuilder builder = new StringBuilder();
        builder.append(supportId);
        builder.append(":");
        builder.append(supportUserId);
        builder.append(":");
        return builder.toString();
    }
}
