package com.tcl.friendadmin.serviceImpl;

import com.tcl.friendadmin.mapper.AdminUserMapper;
import com.tcl.friendadmin.model.AdminUser;
import com.tcl.friendadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminUserMapper mapper;
    @Override
    public AdminUser login(String username, String password) {
        return mapper.login(username,password);
    }
}
