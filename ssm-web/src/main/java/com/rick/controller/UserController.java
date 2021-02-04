/*
 * @Author: Kay_Rick@outlook.com
 * @Date: 2021-01-24 20:51:57
 * @LastEditors: Kay_Rick@outlook.com
 * @LastEditTime: 2021-02-04 13:41:58
 * @Description: 测试SpringMVC
 */
package com.rick.controller;

import com.rick.domain.User;
import com.rick.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 测试 springMVC
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getAllUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<User> getAllUser(HttpServletResponse response, HttpServletRequest request, Model model) {
        log.info("开始获取全部用户数据");
        return userService.queryAll();
    }

}
