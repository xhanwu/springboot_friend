package com.tcl.friendserver.enums;

import lombok.Getter;

/**
 * 用户点赞的状态
 */

@Getter
public enum supportStatusEnum {

    SUPPORT(1, "点赞"),
    UNSUPPORT(0, "取消点赞/未点赞");

    private Integer code;

    private String msg;

    supportStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
