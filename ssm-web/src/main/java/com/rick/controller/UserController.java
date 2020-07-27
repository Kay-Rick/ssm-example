package com.rick.controller;

import com.rick.domain.User;
import com.rick.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Rick
 * @Date: 2020/7/26 23:38
 * @Description:
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 测试 spring mvc
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getAllUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getAllUser(HttpServletRequest request, Model model) {
        List<User> users = userService.queryAll();
        return users.toString();
    }
}
