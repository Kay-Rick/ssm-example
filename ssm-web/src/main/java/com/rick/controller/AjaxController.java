package com.rick.controller;

import com.rick.domain.User;
import com.rick.service.user.UserService;
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
    public static final String ADMIN = "admin";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/a", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<User> ajax1(String name, HttpServletResponse response) throws IOException {
        System.out.println(name);
        return userService.queryAll();
    }
}
