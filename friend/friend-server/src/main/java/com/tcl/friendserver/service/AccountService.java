package com.tcl.friendserver.service;


import com.tcl.friendserver.model.User;
import com.tcl.friendserver.model.UserInfo;

public interface AccountService {

    User selectUserByMobile(String mobile);
    int insertUser(User user);

    UserInfo selectUserInfo(Integer id);
    int insertUserInfo(UserInfo userInfo);
    int updateByPrimaryKeySelective(UserInfo userInfo);
}
