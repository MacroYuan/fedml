package com.myproject.fedml.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    String uploadFiles(MultipartFile file);

    InputStream downloadFiles(String filename);
}
