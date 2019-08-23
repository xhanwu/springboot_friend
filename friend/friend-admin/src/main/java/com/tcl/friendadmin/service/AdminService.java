package com.tcl.friendadmin.service;

import com.tcl.friendadmin.mapper.AdminUserMapper;
import com.tcl.friendadmin.model.AdminUser;

public interface AdminService {
    AdminUser login(String username, String password);
}
