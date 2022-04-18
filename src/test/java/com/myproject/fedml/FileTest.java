package com.myproject.fedml;

import com.myproject.fedml.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author MacroYuan
 * @className FileTest
 * @Date 2022/4/15
 * @Version 1.0
 **/
@SpringBootTest
public class FileTest {

    @Resource
    private FileService fileService;

    @Test
    public void testFileUpload() {
        // 设置存储路径
        fileService.setSavePath("D:\\学习\\2022毕设");

        File file = new File("C:\\Users\\MacroYuan\\Pictures\\背景.png");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), null, fileInputStream);
            MultipartFile uploadFile = multipartFile;
            System.out.println("fileName:" + multipartFile.getName());
            fileService.uploadFiles(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // download需要postman或浏览器测试
}
