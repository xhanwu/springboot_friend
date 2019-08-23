package com.tcl.friendadmin.config.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri =request.getRequestURI() ;
        if (uri.startsWith("/admin")&&null==request.getSession().getAttribute("loginUser")){
            request.getSession().setAttribute("errorMsg","请重新登录");
            System.out.println(request.getContextPath()+"/admin");
            response.sendRedirect(request.getContextPath()+"/admin");
            return false;
        }else {
            request.getSession().removeAttribute("errorMSG");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
