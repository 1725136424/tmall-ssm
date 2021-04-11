package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.dao.user.UserDao;
import com.study.pojo.entity.Page;
import com.study.pojo.user.User;
import com.study.pojo.user.UserExample;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<User> findAll(Page page) {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("uid desc");
        if (page != null) {
            PageHelper.offsetPage(page.getStart(), page.getCount());
        }
        List<User> users = userDao.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        page.setTotal((int) pageInfo.getTotal());
        return users;
    }

    @Override
    public User findById(Integer uid) {
        return userDao.selectByPrimaryKey(uid);
    }

    @Override
    public User findByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userDao.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void save(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public User findByUsernameAndPWD(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria
                .andUsernameEqualTo(user.getUsername())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userDao.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
