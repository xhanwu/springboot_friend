package com.tcl.friendadmin.service;

import com.tcl.friendadmin.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    int deleteBlog(Long id);
    int updateBlog(Blog blog);
    Blog findBlogById(Long l);

}
