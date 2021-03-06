package com.tcl.friendserver.model;

import java.util.Date;

public class FriendWeiboCommentReply {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment_reply.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment_reply.comment_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long commentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment_reply.from_userid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long fromUserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment_reply.to_userid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long toUserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment_reply.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment_reply.comment_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Date commentTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_weibo_comment_reply
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public FriendWeiboCommentReply(Long id, Long commentId, Long fromUserid, Long toUserid, String content, Date commentTime) {
        this.id = id;
        this.commentId = commentId;
        this.fromUserid = fromUserid;
        this.toUserid = toUserid;
        this.content = content;
        this.commentTime = commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_weibo_comment_reply
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public FriendWeiboCommentReply() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment_reply.id
     *
     * @return the value of friend_weibo_comment_reply.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment_reply.id
     *
     * @param id the value for friend_weibo_comment_reply.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment_reply.comment_id
     *
     * @return the value of friend_weibo_comment_reply.comment_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment_reply.comment_id
     *
     * @param commentId the value for friend_weibo_comment_reply.comment_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment_reply.from_userid
     *
     * @return the value of friend_weibo_comment_reply.from_userid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getFromUserid() {
        return fromUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment_reply.from_userid
     *
     * @param fromUserid the value for friend_weibo_comment_reply.from_userid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setFromUserid(Long fromUserid) {
        this.fromUserid = fromUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment_reply.to_userid
     *
     * @return the value of friend_weibo_comment_reply.to_userid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getToUserid() {
        return toUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment_reply.to_userid
     *
     * @param toUserid the value for friend_weibo_comment_reply.to_userid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setToUserid(Long toUserid) {
        this.toUserid = toUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment_reply.content
     *
     * @return the value of friend_weibo_comment_reply.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment_reply.content
     *
     * @param content the value for friend_weibo_comment_reply.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment_reply.comment_time
     *
     * @return the value of friend_weibo_comment_reply.comment_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment_reply.comment_time
     *
     * @param commentTime the value for friend_weibo_comment_reply.comment_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}