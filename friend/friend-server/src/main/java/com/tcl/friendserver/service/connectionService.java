package com.tcl.friendserver.service;

public interface connectionService {

    /**
     * 将Redis里微博的点赞数据存入数据库中
     */
    void transWeiboSupportDataFromRedisToDB();

    /**
     * 将Redis里微博的点赞数量数据存入数据库
     */
    void transWeiboSupportCountFromRedis2DB();

    /**
     * 将Redis里评论的点赞数据存入数据库中
     */
    void transCommentSupportDataFromRedisToDB();

    /**
     * 将Redis里评论的点赞数量数据存入数据库
     */
    void transCommentSupportCountFromRedis2DB();
}
