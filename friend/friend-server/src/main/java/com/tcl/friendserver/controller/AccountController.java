package com.tcl.friendserver.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.friendserver.constant.ReturnConstants;
import com.tcl.friendserver.message.AccountCtrReturnMessage;
import com.tcl.friendserver.message.UserData;
import com.tcl.friendserver.model.User;
import com.tcl.friendserver.model.UserInfo;
import com.tcl.friendserver.service.AccountService;
import com.tcl.friendserver.utils.HttpRequestResponseUtils;
import com.tcl.friendserver.utils.JwtUtil;
import com.tcl.friendserver.utils.TextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AccountController {
    private static final Logger Log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/account")
    public String accountRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Log.info("accountRegister : ++");
        String mobile = request.getParameter(ReturnConstants.MOBILE);
        String nickname = request.getParameter(ReturnConstants.NICKNAME);
        String password = request.getParameter(ReturnConstants.PASSWORD);
        //默认nickname为password
        if (TextUtils.isEmpty(nickname)) {
            nickname = mobile;
        }
        Log.info("accountRegister : mobile = " + mobile + ",nickname = " + nickname + ",password = " + password);

        if (!TextUtils.isPhoneNumber(mobile)) {
            return JSON.toJSONString(AccountCtrReturnMessage.error()
                    .message(ReturnConstants.RETURN_ERROR_REGISTER_MOBILE));
        }

        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(nickname) || TextUtils.isEmpty(password)) {
            return JSON.toJSONString(AccountCtrReturnMessage.error()
                    .message(ReturnConstants.RETURN_ERROR_REGISTER_PARAM));
        } else if (accountService.selectUserByMobile(mobile) != null) {
            return JSON.toJSONString(AccountCtrReturnMessage.error()
                    .message(ReturnConstants.RETURN_ERROR_REGISTER_USER_EXIST));
        }

        //TODO 确认新注册用户默认权限信息
        //插入账户信息，这个insertId是插入成功的数量
        accountService.insertUser(new User(null, mobile, password, "user"));

        //更新用户详细信息数据
        User user = accountService.selectUserByMobile(mobile);
        accountService.insertUserInfo(new UserInfo(user.getUid(), nickname));

        //构造返回给前端的JWT Token
        String jwtToken = JwtUtil.sign(JSON.toJSONString(new User(user.getUid(), user.getMobile(), null, null)));

        return JSON.toJSONString(AccountCtrReturnMessage.success()
                .message(ReturnConstants.RETURN_SUCCESS_NOTE)
                .jwtToken(jwtToken)
                .data(new User(user.getUid(), user.getMobile(), null, user.getPerms())));
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authorization")
    public String accountLogin(HttpServletRequest request, HttpServletResponse response) {
        Log.info("accountLogin ++");
        String mobile = request.getParameter(ReturnConstants.MOBILE);
        String password = request.getParameter(ReturnConstants.PASSWORD);
        Log.info("accountLogin : mobile = " + mobile + ",password = " + password);
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)) {
            return JSON.toJSONString(AccountCtrReturnMessage.success()
                    .message(ReturnConstants.RETURN_ERROR_LOGIN_MISS_PARAM));
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(mobile, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException exception) {
            return JSON.toJSONString(AccountCtrReturnMessage.error()
                    .message(ReturnConstants.RETURN_ERROR_LOGIN_FAILED));
        }
        //登录成功
        User user = (User) subject.getPrincipal();
        UserInfo userInfo = accountService.selectUserInfo(user.getUid());

        String jwtToken = JwtUtil.sign(JSON.toJSONString(new User(user.getUid(), user.getMobile(), null, null)));

        return JSON.toJSONString(AccountCtrReturnMessage.success()
                .message(ReturnConstants.RETURN_SUCCESS_NOTE)
                .jwtToken(jwtToken)
                .data(new UserData(user.getUid(), user.getMobile(), userInfo.getNickname())));
    }

    /**
     * 用户注销
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String accountLogout(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    @RequestMapping("/user_data")
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Log.info("getUserInfo : ++");
        String jwtToken = request.getHeader("Token");
        String jwtPayload = JwtUtil.unSign(jwtToken);
        Log.info("getUserInfo : jwtPayload = " + jwtPayload);
        if (jwtPayload == null) {
            return JSON.toJSONString(AccountCtrReturnMessage.error().message(ReturnConstants.RETURN_ERROR_JWT_TIMEOUT));
        }
        User user = JSON.parseObject(jwtPayload, User.class);
        Log.info("getUserInfo : user = " + user);
        UserInfo userInfo = null;
        if (user != null) {
            userInfo = accountService.selectUserInfo(user.getUid());
        }
        return JSON.toJSONString(AccountCtrReturnMessage.success().message(ReturnConstants.RETURN_SUCCESS_NOTE).data(userInfo));
    }

    @RequestMapping("/set_user_data")
    public String setUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Log.info("setUserInfo : ++");
        String jwtToken = request.getHeader("Token");
        String jwtPayload = JwtUtil.unSign(jwtToken);
        Log.info("setUserInfo : jwtPayload = " + jwtPayload);
        if (jwtPayload == null) {
            return JSON.toJSONString(AccountCtrReturnMessage.error().message(ReturnConstants.RETURN_ERROR_JWT_TIMEOUT));
        }
        User user = JSON.parseObject(jwtPayload, User.class);
        UserInfo userInfo = (UserInfo) HttpRequestResponseUtils.extraObjectFromRequest(request, UserInfo.class);
        Log.info("setUserInfo : user info = = " + userInfo);
        if (user != null) {
            userInfo.setId(user.getUid());
            accountService.updateByPrimaryKeySelective(userInfo);
            return JSON.toJSONString(AccountCtrReturnMessage.success().message(ReturnConstants.RETURN_SUCCESS_NOTE));
        } else {
            return JSON.toJSONString(AccountCtrReturnMessage.error().message(ReturnConstants.RETURN_ERROR_USERINFO));
        }
    }
}
