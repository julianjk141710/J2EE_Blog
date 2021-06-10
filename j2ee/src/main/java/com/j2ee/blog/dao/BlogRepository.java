package com.j2ee.blog.dao;

import com.j2ee.blog.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *可用于动态生成query，帮我们提供了一个高级的入口和结构
 * springboot提供的多条件查询借口
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

}
