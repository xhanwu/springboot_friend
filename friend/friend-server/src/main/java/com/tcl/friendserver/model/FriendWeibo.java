package com.tcl.friendserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class FriendWeibo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    @Id
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.uid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.source_id
     *
     * @mbggenerated Wed Aug 21 18:11:48 CST 2019
     */
    private Long sourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.image_url
     *
     * @mbg.generated Thu Aug 22 14:59:38 CST 2019
     */
    private String imageUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.create_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.status
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.is_top
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Boolean isTop;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.is_comment
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Boolean isComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.support_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Integer supportCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.comment_count
     *
     * @mbggenerated Wed Aug 21 18:11:48 CST 2019
     */
    private Integer commentCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.weibo_type
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private String weiboType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.crowd_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Long crowdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.repost_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private Integer repostCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_weibo.from_device
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    private String fromDevice;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_weibo
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public FriendWeibo(Long id, Long uid, Long sourceId, String content, String imageUrl, Date createTime, Boolean status, Boolean isTop, Boolean isComment, Integer supportCount, Integer commentCount, String weiboType, Long crowdId, Integer repostCount, String fromDevice) {
        this.id = id;
        this.uid = uid;
        this.sourceId = sourceId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createTime = createTime;
        this.status = status;
        this.isTop = isTop;
        this.isComment = isComment;
        this.supportCount = supportCount;
        this.commentCount = commentCount;
        this.weiboType = weiboType;
        this.crowdId = crowdId;
        this.repostCount = repostCount;
        this.fromDevice = fromDevice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_weibo
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public FriendWeibo() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.id
     *
     * @return the value of friend_weibo.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.id
     *
     * @param id the value for friend_weibo.id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.uid
     *
     * @return the value of friend_weibo.uid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.uid
     *
     * @param uid the value for friend_weibo.uid
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.source_id
     *
     * @return the value of friend_weibo.source_id
     *
     * @mbggenerated Wed Aug 21 18:11:48 CST 2019
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.source_id
     *
     * @param sourceId the value for friend_weibo.source_id
     *
     * @mbggenerated Wed Aug 21 18:11:48 CST 2019
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.content
     *
     * @return the value of friend_weibo.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.content
     *
     * @param content the value for friend_weibo.content
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.image_url
     *
     * @return the value of friend_weibo.image_url
     *
     * @mbg.generated Thu Aug 22 14:59:38 CST 2019
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.image_url
     *
     * @param imageUrl the value for friend_weibo.image_url
     *
     * @mbg.generated Thu Aug 22 14:59:38 CST 2019
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.create_time
     *
     * @return the value of friend_weibo.create_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.create_time
     *
     * @param createTime the value for friend_weibo.create_time
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.status
     *
     * @return the value of friend_weibo.status
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.status
     *
     * @param status the value for friend_weibo.status
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.is_top
     *
     * @return the value of friend_weibo.is_top
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.is_top
     *
     * @param isTop the value for friend_weibo.is_top
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.is_comment
     *
     * @return the value of friend_weibo.is_comment
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Boolean getIsComment() {
        return isComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.is_comment
     *
     * @param isComment the value for friend_weibo.is_comment
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.support_count
     *
     * @return the value of friend_weibo.support_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Integer getSupportCount() {
        return supportCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.support_count
     *
     * @param supportCount the value for friend_weibo.support_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setSupportCount(Integer supportCount) {
        this.supportCount = supportCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.comment_count
     *
     * @return the value of friend_weibo.comment_count
     *
     * @mbggenerated Wed Aug 21 18:11:48 CST 2019
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.comment_count
     *
     * @param commentCount the value for friend_weibo.comment_count
     *
     * @mbggenerated Wed Aug 21 18:11:48 CST 2019
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.weibo_type
     *
     * @return the value of friend_weibo.weibo_type
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public String getWeiboType() {
        return weiboType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.weibo_type
     *
     * @param weiboType the value for friend_weibo.weibo_type
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setWeiboType(String weiboType) {
        this.weiboType = weiboType == null ? null : weiboType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.crowd_id
     *
     * @return the value of friend_weibo.crowd_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Long getCrowdId() {
        return crowdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.crowd_id
     *
     * @param crowdId the value for friend_weibo.crowd_id
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setCrowdId(Long crowdId) {
        this.crowdId = crowdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.repost_count
     *
     * @return the value of friend_weibo.repost_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public Integer getRepostCount() {
        return repostCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.repost_count
     *
     * @param repostCount the value for friend_weibo.repost_count
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setRepostCount(Integer repostCount) {
        this.repostCount = repostCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_weibo.from_device
     *
     * @return the value of friend_weibo.from_device
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public String getFromDevice() {
        return fromDevice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_weibo.from_device
     *
     * @param fromDevice the value for friend_weibo.from_device
     *
     * @mbg.generated Tue Aug 13 17:44:45 CST 2019
     */
    public void setFromDevice(String fromDevice) {
        this.fromDevice = fromDevice == null ? null : fromDevice.trim();
    }

    @Override
    public String toString() {
        return "FriendWeibo{" +
                "id=" + id +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", isTop=" + isTop +
                ", isComment=" + isComment +
                ", supportCount=" + supportCount +
                ", weiboType='" + weiboType + '\'' +
                ", crowdId=" + crowdId +
                ", repostCount=" + repostCount +
                ", fromDevice='" + fromDevice + '\'' +
                '}';
    }
}