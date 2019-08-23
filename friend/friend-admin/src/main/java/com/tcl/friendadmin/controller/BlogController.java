package com.tcl.friendadmin.controller;

import com.tcl.friendadmin.config.tips.ErrorTip;
import com.tcl.friendadmin.config.tips.SuccessTip;
import com.tcl.friendadmin.config.tips.Tip;
import com.tcl.friendadmin.model.Blog;
import com.tcl.friendadmin.service.BlogService;
import com.tcl.friendadmin.utils.BeanKit;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@MapperScan("com.tcl.friendadmin.mapper")
@RequestMapping("/admin")
public class BlogController extends BaseController {
    @Resource
    private BlogService blogService;

    @GetMapping("/blog")
    public String Blog(HttpServletRequest request){
        request.setAttribute("path","Blog");
        return "admin/Blog";
    }

    /*查询所有博客*/
    @GetMapping("/blog/blogs")
    @ResponseBody
    public Tip getBlogsList(@RequestParam Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            System.out.println("参数异常");
        }
        return new SuccessTip(blogService.getAllBlogs());
    }
    /*搜索博客*/
    @GetMapping("/blog/blogs/search")
    @ResponseBody
    public Tip getBlogById(@RequestParam Map<String,Object> params){
        String number = String.valueOf(params.get("id"));
        if ( "".equals(number) || number==null){
            return getBlogsList(params);
        }
        Long ID = Long.parseLong(number);
        List<Blog> blog = new ArrayList<>();
        try{
            blog.add(blogService.findBlogById(ID));
            if (blog.get(0)==null){
                return new ErrorTip(blogService.getAllBlogs());
            }
        }catch (Exception e){
            return new ErrorTip();
        }
        System.out.println("success selected blog");
        return new SuccessTip(blog);
    }

    /*删除博客*/
    @PostMapping("/blog/delete")
    @ResponseBody
    public Tip deleteBlog(@RequestParam Map<String,Object> params){
        System.out.println("delete:"+params);
        Long ID = Long.parseLong(String.valueOf(params.get("id")));
        System.out.println(ID);
        if (deleteBlog(ID)!=1){
            return new ErrorTip();
        }
    return new SuccessTip();
    }

    /*更改博客*/
    @PostMapping("/blog/edit")
    @ResponseBody
    public Tip editBlog(@RequestParam Map<String ,Object> params){
        Blog blog =  BeanKit.fillBeanWithMap(params,new Blog());
        if (editBlog(blog)!=1){
            return new ErrorTip();
        }
        return new SuccessTip();
    }

    @Transactional
    public int deleteBlog(Long id){
        if (blogService.deleteBlog(id)!= 1){
            System.out.println("删除用户失败");
            return -1 ;
        }
        return  1;
    }

    @Transactional
    public int editBlog(Blog blog){
        if ( blogService.updateBlog(blog)!=1){
            System.out.println("更改用户信息失败");
            return -1;
        }
        return 1;
    }


}
