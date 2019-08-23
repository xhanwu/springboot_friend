package com.tcl.friendadmin.config.tips;


import com.tcl.friendadmin.config.BizExceptionEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回给前台的错误提示
 *
 * @author added by xiangtian.meng
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorTip(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.message = bizExceptionEnum.getMessage();
    }
    public ErrorTip(){
        super.code = 0; //success code is 0
        super.message = "操作失敗";
        super.count = "1";
        List list = new ArrayList();
        super.data = list;
    }
    public ErrorTip(List list){
        super.code = 0; //success code is 0
        super.message = "操作失敗";
        super.count = "1";
        super.data = list;
    }
    public ErrorTip(String message){
        super.code = 0;
        super.message = message;
        super.count= "1";
    }
}
