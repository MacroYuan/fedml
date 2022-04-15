package com.myproject.fedml.service.impl;

import com.myproject.fedml.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

/**
 * @author MacroYuan
 * @className FileServiceImpl
 * @Date 2022/4/6
 * @Version 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.save-path}")
    private String savePath;

    @Override
    public String uploadFiles(MultipartFile file) {
        String result = null;
        final long MAX_SIZE = 2097152L;

        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        if (file.getSize() > MAX_SIZE) {
            return null;
        }
        String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        String newName = System.currentTimeMillis() + suffixName;
        File newFile = new File(savePath, newName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        try {
            file.transferTo(newFile);
            result = savePath + "/"+ newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public InputStream downloadFiles(String filename) {
        File file = new File(savePath, filename);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
