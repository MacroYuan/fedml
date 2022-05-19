package com.myproject.fedml.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    /**
     * 上传文件
     * @param file
     * @return 文件完整路径
     */
    String uploadFiles(MultipartFile file);

    /**
     * 下载文件
     * @param filename
     * @return 文件输入流
     */
    InputStream downloadFiles(String filename);

    /**
     * 工具函数，用于不同使用fileService服务的用户，设置自己的存储路径
     * @param savePath
     */
    void setSavePath(String savePath);

    public String uploadFilesWithOriginName(MultipartFile file);
}
