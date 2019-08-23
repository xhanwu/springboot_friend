package com.tcl.friendserver.message;

public class UserData {
    private Integer uid;
    private String mobile;
    private String nickname;

    public UserData() {
    }

    public UserData(Integer uid, String mobile, String nickname) {
        this.uid = uid;
        this.mobile = mobile;
        this.nickname = nickname;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "uid='" + uid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
