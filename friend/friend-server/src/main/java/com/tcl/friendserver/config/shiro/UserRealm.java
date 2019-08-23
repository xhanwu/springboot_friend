package com.tcl.friendserver.config.shiro;

import com.tcl.friendserver.model.User;
import com.tcl.friendserver.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    private static final Logger Log = LoggerFactory.getLogger(AuthorizingRealm.class);
    @Autowired
    AccountService accountService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Log.info("doGetAuthorizationInfo : ++");
        Subject subject = SecurityUtils.getSubject();
        User myUser = (User) subject.getPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //这里可以通过user从数据库获取其权限信息
        simpleAuthorizationInfo.addStringPermission(myUser.getPerms());
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Log.info("doGetAuthenticationInfo : ++");
        UsernamePasswordToken user = (UsernamePasswordToken) authenticationToken;
        //这里的username就是mobile
        String mobile = user.getUsername();
        User myUser = accountService.selectUserByMobile(mobile);
        if (myUser != null)
            //这里只需要将获取到的用户信息返回即可，shiro框架内会对AuthenticationToken的password和SimpleAuthenticationInfo的credentials进行比较
            return new SimpleAuthenticationInfo(myUser, myUser.getPassword(), "");
        return null;
    }
}
