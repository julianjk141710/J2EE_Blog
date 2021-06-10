package com.j2ee.blog.service;

import com.j2ee.blog.po.Blog;
import com.j2ee.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

//public interface BlogService {
//
//    Blog getBlog(Long id);
//
//    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
//
//    Blog saveBlog(Blog blog);
//
//    Blog updateBlog(Long id, Blog blog);
//
//    void deleteBlog(Long id);
//}



/**
 * Created by limi on 2017/10/20.
 */
public interface BlogService {

    Blog getBlog(Long id);


    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
