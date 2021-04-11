package com.study.service;

import com.study.pojo.entity.Page;
import com.study.pojo.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll(Page page);

    User findById(Integer uid);

    User findByUsername(String username);

    void save(User user);

    User findByUsernameAndPWD(User user);
}
