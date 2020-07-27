package com.rick.service.user.Impl;

import com.rick.dao.user.UserMapper;
import com.rick.domain.User;
import com.rick.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Rick
 * @Date: 2020/7/26 22:18
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }
}
