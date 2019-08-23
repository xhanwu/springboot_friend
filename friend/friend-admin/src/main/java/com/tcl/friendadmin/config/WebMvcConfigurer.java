package com.tcl.friendadmin.config;

import com.tcl.friendadmin.config.intercepter.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin").excludePathPatterns("/admin/login");
    }
}
