/*
 * @Author: Kay_Rick@outlook.com
 * @Date: 2021-02-04 13:28:35
 * @LastEditors: Kay_Rick@outlook.com
 * @LastEditTime: 2021-02-04 16:24:19
 * @Description: 测试文件上传与下载
 */
package com.rick.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${file.uploadPath}")
    public String uploadPath;

    @Value("${file.downloadPath}")
    public String downloadPath;

    /**
     * 文件上传
     * 
     * @param username
     * @param file
     * @param files
     * @return
     */
    @RequestMapping("/upload")
    public String upload(String username, @RequestPart("photo") MultipartFile file,
            @RequestPart("files") MultipartFile[] files, Model model) {
        log.info("上传的信息：username={}, fileSize={}, fileNum={}", username, file.getSize(), files.length);
        if (!file.isEmpty()) {
            try {
                file.transferTo(new File(uploadPath + file.getOriginalFilename()));
            } catch (Exception e) {
                log.error("{}上传失败：{}", file.getOriginalFilename(), e.getMessage());
                return "上传失败";
            }
            log.info("{}上传成功", file.getOriginalFilename());
        }
        if (files.length > 0) {
            for (MultipartFile item : files) {
                try {
                    item.transferTo(new File(uploadPath + item.getOriginalFilename()));
                } catch (Exception e) {
                    log.error("{}上传失败：{}", item.getOriginalFilename(), e.getMessage());
                    model.addAttribute("msg", "文件上传失败");
                }
                log.info("{}上传成功", item.getOriginalFilename());
            }
        }
        model.addAttribute("msg", "Success");
        return "forward:/index.jsp";
    }
    
    /**
     * 文件下载
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long id) {
        String realFilePath = downloadPath + id.toString() + ".jpg";
        FileSystemResource file = new FileSystemResource(realFilePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ResponseEntity<InputStreamResource> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (Exception e) {
            log.error("文件：{}下载失败：{}", realFilePath, e.getMessage());
        }
        log.info("文件：{}下载成功", realFilePath);
        return responseEntity;
    }

    // /**
    //  * JavaWeb方式下载文件
    //  * 
    //  * @param request
    //  * @param response
    //  * @throws Exception
    //  */
    // @RequestMapping("webdownload")
    // public void download(HttpServletRequest request, HttpServletResponse response) {
    //     // 获取要下载的文件名：以webapp中file目录下
    //     String downloadFileName = "GavinThomas.jpg";
    //     // 读取要下载的文件内容
    //     ServletContext servletContext = request.getServletContext();
    //     // 获取要下载的文件类型
    //     String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
    //     log.info("下载的文件类型为：{}", mimeType);
    //     try {
    //         // 设置文件类型
    //         response.setContentType(mimeType);
    //         // URLEncoder.encode()来解决中文乱码问题
    //         response.setHeader("Content-Disposition",
    //                 "attachment; filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
    //         InputStream is = servletContext.getResourceAsStream("/file/" + downloadFileName);
    //         OutputStream os = response.getOutputStream();
    //         // 读取流中的全部数据，复制给输出流，输出给客户端
    //         IOUtils.copy(is, os);
    //     } catch (Exception e) {
    //         log.error("文件：{}下载失败：{}", downloadFileName, e.getMessage());
    //     }
    //     log.info("文件：{}下载成功", downloadFileName);
    // }

}
