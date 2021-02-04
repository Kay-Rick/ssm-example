/*
 * @Author: Kay_Rick@outlook.com
 * @Date: 2021-01-24 21:16:09
 * @LastEditors: Kay_Rick@outlook.com
 * @LastEditTime: 2021-02-04 13:28:14
 * @Description: 测试Ajax
 */
package com.rick.controller;

import com.rick.domain.User;
import com.rick.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/a", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<User> ajax1(String name, HttpServletResponse response) throws IOException {
        log.info("Ajax传递参数：name：{}", name);
        return userService.queryAll();
    }
}
