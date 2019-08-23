package com.tcl.friendserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class FriendWeiboComment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    @Id
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.weibo_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long weiboId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.user_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.create_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.commentreply_count
     *
     * @mbg.generated Thu Aug 22 09:17:44 CST 2019
     */
    private Integer commentreplyCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo_comment.support_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Integer supportCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_weibo_comment
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public FriendWeiboComment(Long id, Long weiboId, Long userId, String content, Date createTime, Integer commentreplyCount, Integer supportCount) {
        this.id = id;
        this.weiboId = weiboId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
        this.commentreplyCount = commentreplyCount;
        this.supportCount = supportCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_weibo_comment
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public FriendWeiboComment() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.id
     *
     * @return the value of friend_weibo_comment.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.id
     *
     * @param id the value for friend_weibo_comment.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.weibo_id
     *
     * @return the value of friend_weibo_comment.weibo_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getWeiboId() {
        return weiboId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.weibo_id
     *
     * @param weiboId the value for friend_weibo_comment.weibo_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.user_id
     *
     * @return the value of friend_weibo_comment.user_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.user_id
     *
     * @param userId the value for friend_weibo_comment.user_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.content
     *
     * @return the value of friend_weibo_comment.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.content
     *
     * @param content the value for friend_weibo_comment.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.create_time
     *
     * @return the value of friend_weibo_comment.create_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.create_time
     *
     * @param createTime the value for friend_weibo_comment.create_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.commentreply_count
     *
     * @return the value of friend_weibo_comment.commentreply_count
     *
     * @mbg.generated Thu Aug 22 09:17:44 CST 2019
     */
    public Integer getCommentreplyCount() {
        return commentreplyCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.commentreply_count
     *
     * @param commentreplyCount the value for friend_weibo_comment.commentreply_count
     *
     * @mbg.generated Thu Aug 22 09:17:44 CST 2019
     */
    public void setCommentreplyCount(Integer commentreplyCount) {
        this.commentreplyCount = commentreplyCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo_comment.support_count
     *
     * @return the value of friend_weibo_comment.support_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Integer getSupportCount() {
        return supportCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo_comment.support_count
     *
     * @param supportCount the value for friend_weibo_comment.support_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setSupportCount(Integer supportCount) {
        this.supportCount = supportCount;
    }
}