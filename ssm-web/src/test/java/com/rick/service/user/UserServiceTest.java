package com.rick.service.user;

import com.rick.dao.user.UserMapper;
import com.rick.domain.User;
import com.rick.service.user.Impl.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @Author: Rick
 * @Date: 2020/7/27 01:21
 * @Description: 利用Mockito测试框架进行单元测试
 */
public class UserServiceTest {
    
    @Mock
    private UserMapper userMapper;
    
    @InjectMocks
    private UserServiceImpl userService;

    private static final User user1 = new User();
    private static final User user2 = new User();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        prepareData();
    }

    /**
     * 准备数据
     */
    private void prepareData () {
        user1.setName("Rick");
        user2.setName("kay");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        when(userMapper.queryAll()).thenReturn(list);
    }

    @Test
    public void queryAllTest() {
        List<User> userList = userService.queryAll();
        Assert.assertEquals("Rick", userList.get(0).getName());
    }

    @After
    public void tearDown() throws Exception {
    }
}
