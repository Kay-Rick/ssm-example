package com.rick.controller;

import com.rick.domain.User;
import com.rick.service.user.UserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 测试 spring mvc
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getAllUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getAllUser(HttpServletResponse response, HttpServletRequest request, Model model) {
        List<User> users = userService.queryAll();
        return users.toString();
    }


    /**
     * 测试文件上传：如果是多文件上传，则使用MutipartFile数组来接受
     * @param username
     * @param file
     * @param model
     * @return
     */
    @RequestMapping("/upload")
    public String upload(String username, @RequestParam("photo") MultipartFile file, Model model) {
        System.out.println(username);
        System.out.println("上传的文件信息：");
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        try {
            file.transferTo(new File("D:\\upload\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            model.addAttribute("msg", "文件上传失败");
            e.printStackTrace();
        }
        model.addAttribute("msg", "Success");
        return "forward:/index.jsp";
    }


    /**
     * JavaWeb方式下载文件
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取要下载的文件名
        String downloadFileName = "儿子.jpg";
        // 读取要下载的文件内容
        ServletContext servletContext = request.getServletContext();
        // 获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("下载的文件类型：" + mimeType);
        // 设置文件类型
        response.setContentType(mimeType);
        // URLEncoder.encode()来解决中文乱码问题
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
        InputStream is = servletContext.getResourceAsStream("/file/" + downloadFileName);
        OutputStream os = response.getOutputStream();
        // 读取流中的全部数据，复制给输出流，输出给客户端
        IOUtils.copy(is, os);
    }

    /**
     * SpringMVC文件下载
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("load")
    public ResponseEntity<byte[]> download_mvc(HttpServletRequest request) throws Exception {
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/file/儿子.jpg");
        FileInputStream is = new FileInputStream(realPath);
        byte[] temp = new byte[is.available()];
        is.read(temp);
        is.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Disposition", "attachment; filename=" + URLEncoder.encode("儿子.jpg", "UTF-8"));
        return new ResponseEntity<byte[]>(temp, httpHeaders, HttpStatus.OK);
    }
}
