package com.j2ee.blog.dao;

import com.j2ee.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
//User 和 User的主键类型
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
