package com.tcl.friendadmin.controller;

import com.tcl.friendadmin.config.tips.ErrorTip;
import com.tcl.friendadmin.config.tips.Tip;
import com.tcl.friendadmin.model.AdminUser;
import com.tcl.friendadmin.service.AdminService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@MapperScan("com.tcl.friendadmin.mapper")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;

    @GetMapping
    public String login(){
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "userName")String name,
                 @RequestParam(value = "password")String password,
                 HttpSession session){
        if ("".equals(password) || password==null){
            session.setAttribute("errorMsg","密码不能为空");
            return "admin/login";
        }
        if ("".equals(name) || name==null){
            session.setAttribute("errorMsg","用户名不能为空");
            return "admin/login";
        }
        AdminUser adminUser =adminService.login(name,password);
        if (adminUser!=null){
            session.setAttribute("loginUser",adminUser.getNickname());
            session.setAttribute("loginUserId",adminUser.getAdminUserId());
            session.setMaxInactiveInterval(60*10);
            return "admin/blog";
        }else {
            session.setAttribute("errorMsg","登录失败");
            return "admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }




}
