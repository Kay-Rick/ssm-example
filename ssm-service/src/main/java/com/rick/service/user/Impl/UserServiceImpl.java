package com.rick.service.user.Impl;

import com.google.gson.Gson;
import com.rick.dao.user.UserMapper;
import com.rick.domain.User;
import com.rick.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        log.info("准备插入用户信息：{}", new Gson().toJson(user));
        userMapper.insertUser(user);
        log.info("插入用户信息成功");
    }

    @Override
    public List<User> queryAll() {
        log.info("开始查询所有用户信息");
        List<User> result = userMapper.queryAll();
        log.info("查询所有用户信息结果：{}", new Gson().toJson(result));
        return result;
    }
}
