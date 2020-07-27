package com.rick.service.user;

import com.rick.domain.User;


import java.util.List;

/**
 * @Author: Rick
 * @Date: 2020/7/26 22:17
 * @Description:
 */
public interface UserService {
    void insertUser(User user);
    List<User> queryAll();
}
