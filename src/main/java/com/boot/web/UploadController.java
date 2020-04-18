package com.boot.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author liqiang
 * @date 2020/4/11 0011 17:25
 */
@Controller
public class UploadController {
    @Value("${filePath}")
    private String filePath;
    @GetMapping("/upload")
    public String uploading() {
        //跳转到 templates 目录下的 uploading.html
        return "uploading";
    }

    //处理文件上传
    @PostMapping("/uploading")
    @ResponseBody
    public String uploading(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            uploadFile(file.getBytes(), filePath, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件上传失败!");
            return "uploading failure";
        }
        System.out.println("文件上传成功!");
        return "uploading success";
    }



    public void  uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello,StringBoot!";
    }
}
