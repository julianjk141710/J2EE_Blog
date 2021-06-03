package com.j2ee.blog.service;

import com.j2ee.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
