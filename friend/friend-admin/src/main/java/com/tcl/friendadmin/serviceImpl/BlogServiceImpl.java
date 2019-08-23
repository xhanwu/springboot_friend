package com.tcl.friendadmin.serviceImpl;

import com.tcl.friendadmin.mapper.BlogMapper;
import com.tcl.friendadmin.model.Blog;
import com.tcl.friendadmin.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper mapper;

    @Override
    public List<Blog> getAllBlogs() {
        return mapper.findAllBlogs();
    }

    @Override
    public int deleteBlog(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        return mapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    public Blog findBlogById(Long l) {
        return mapper.selectByPrimaryKey(l);
    }
}
