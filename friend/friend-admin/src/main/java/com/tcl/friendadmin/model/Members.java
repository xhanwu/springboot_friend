package com.tcl.friendadmin.model;

public class Members {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.uid
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mobile
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.perms
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    private String perms;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public Members(Integer uid, String mobile, String password, String perms) {
        this.uid = uid;
        this.mobile = mobile;
        this.password = password;
        this.perms = perms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public Members() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.uid
     *
     * @return the value of user.uid
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.uid
     *
     * @param uid the value for user.uid
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mobile
     *
     * @return the value of user.mobile
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mobile
     *
     * @param mobile the value for user.mobile
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.perms
     *
     * @return the value of user.perms
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public String getPerms() {
        return perms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.perms
     *
     * @param perms the value for user.perms
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }
}