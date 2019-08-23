package com.tcl.friendadmin.config.tips;

import java.util.List;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author added by xiangtian.meng
 */
public abstract class Tip {

    protected int code;
    protected String message;
    protected String count;
    protected List data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
