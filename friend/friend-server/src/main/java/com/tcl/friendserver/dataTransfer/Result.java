package com.tcl.friendserver.dataTransfer;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1491499610244557029L;

    public static final int SUCCESS = 200;
    public static final int ERROR = -1;

    /**
     * 状态码 0: success, -1: error
     */
    private int code;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 结果数据
     */
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> Result<T> successMessage(String message) {
        return success(message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(Result.SUCCESS, message, data);
    }

    public static <T> Result<T> failure(String message) {
        return failure(Result.ERROR, message);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }

    public boolean isOk() {
        return code == SUCCESS;
    }

}
