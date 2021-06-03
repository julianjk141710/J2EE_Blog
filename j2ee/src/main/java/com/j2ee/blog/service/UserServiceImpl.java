package com.j2ee.blog.service;

import com.j2ee.blog.dao.UserRepository;
import com.j2ee.blog.po.User;
import com.j2ee.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//不加注解无法使用service
@Service
public class UserServiceImpl implements UserService{
    //注入UserRepository
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
