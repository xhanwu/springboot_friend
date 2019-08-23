package com.tcl.friendserver.constant;

public class ReturnConstants {
    public static final String MOBILE = "mobile";
    public static final String NICKNAME = "nickname";
    public static final String PASSWORD = "password";

    public static final int RETURN_ERROR = 101;
    public static final int RETURN_SUCCESS = 200;

    //about login
    public static final String RETURN_ERROR_NOTE = "failed";
    public static final String RETURN_ERROR_LOGIN_MISS_PARAM = "miss mobile or password";
    public static final String RETURN_ERROR_LOGIN_FAILED = "mobile or password error";
    //about register
    public static final String RETURN_ERROR_REGISTER_PARAM = "miss parameter";
    public static final String RETURN_ERROR_REGISTER_MOBILE = "mobile format error";
    public static final String RETURN_ERROR_REGISTER_USER_EXIST = "User exist!";
    //others
    public static final String RETURN_ERROR_USERINFO = "User info update failed!";
    public static final String RETURN_ERROR_JWT_TIMEOUT = "Jwt token timeout";
    public static final String RETURN_SUCCESS_NOTE = "success";

    /**
     * 圈子id
     */
    //运动
    public static final int CROWD_ID_SPPORT = 1;
    //化妆
    public static final int CROWD_ID_MAKE_UP = 1 << 1;
    //旅游
    public static final int CROWD_ID_TRAVEL = 1 << 2;
    //书籍
    public static final int CROWD_ID_BOOK = 1 << 3;
    //电影
    public static final int CROWD_ID_MOVIE = 1 << 4;
    //工作
    public static final int CROWD_ID_WORK = 1 << 5;
    //音乐
    public static final int CROWD_ID_MUSIC = 1 << 6;
    //摄影
    public static final int CROWD_ID_POTO = 1 << 7;
    //科技
    public static final int CROWD_ID_TECH = 1 << 8;
    //娱乐
    public static final int CROWD_ID_ENTERTAINMENT = 1 << 9;
}
