package com.tcl.friendserver.serviceImpl;

import com.tcl.friendserver.mapper.UserInfoMapper;
import com.tcl.friendserver.mapper.UserMapper;
import com.tcl.friendserver.model.User;
import com.tcl.friendserver.model.UserInfo;
import com.tcl.friendserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    /*
    API for user DB
     */
    @Override
    public User selectUserByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    /*
    API for user info DB
     */
    @Override
    public UserInfo selectUserInfo(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }



}
