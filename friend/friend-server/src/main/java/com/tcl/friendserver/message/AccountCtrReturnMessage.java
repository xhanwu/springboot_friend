package com.tcl.friendserver.message;


import com.tcl.friendserver.constant.ReturnConstants;
import javafx.scene.chart.PieChartBuilder;

/**
 *
 * 返回信息格式示例如下：
 * {
 *     "code":
 *     "info":
 *     "data":
 *         "auth": 登录信息；{
 *             "uid" ：用户ID
 *             "mobile": 手机号
 *             "nickname" ： 用户名
 *          }
 *
 * }
 */
public class AccountCtrReturnMessage {
    private int code;
    private String info;
    private Object data;
    private String jwtToken;

    public AccountCtrReturnMessage() {
    }

    public AccountCtrReturnMessage(int code, String info, Object data, String jwtToken) {
        this.code = code;
        this.info = info;
        this.data = data;
        this.jwtToken = jwtToken;
    }

    public AccountCtrReturnMessage(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "AccountCtrReturnMessage{" +
                "code=" + code +
                ", info='" + info + '\'' +
                ", data=" + data +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }

    public static AccountCtrReturnMessage success() {
        return new AccountCtrReturnMessage(ReturnConstants.RETURN_SUCCESS);
    }

    public static AccountCtrReturnMessage error() {
        return new AccountCtrReturnMessage(ReturnConstants.RETURN_ERROR);
    }

    public AccountCtrReturnMessage message(String message){
        this.setInfo(message);
        return this;
    }

    public AccountCtrReturnMessage data(Object data){
        this.setData(data);
        return this;
    }

    public AccountCtrReturnMessage jwtToken(String jwtToken){
        this.setJwtToken(jwtToken);
        return this;
    }
}
