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

        // 文件新名字加个时间戳，确保不重复
        String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        String oldName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
        String newName = oldName + "_" + System.currentTimeMillis() + suffixName;
        File newFile = new File(savePath, newName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        try {
            file.transferTo(newFile);
            // windows的"/"改为"\"
            result = savePath + File.separator + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String uploadFilesWithOriginName(MultipartFile file) {
        String result = null;
        final long MAX_SIZE = 2097152L;

        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        if (file.getSize() > MAX_SIZE) {
            return null;
        }


        File newFile = new File(savePath, fileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        try {
            file.transferTo(newFile);
            // windows的"/"改为"\"
            result = savePath + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public InputStream downloadFiles(String fileName) {
        File file = new File(fileName);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

}
