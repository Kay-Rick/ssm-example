package com.rick.dao.user;

import com.rick.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Rick
 * @Date: 2020/7/26 22:20
 * @Description:
 */
@Mapper
public interface UserMapper {
    void insertUser(User user);
    List<User> queryAll();
}
