package com.myproject.fedml.service;

import com.myproject.fedml.mbg.model.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author MacroYuan
 * @className ModelService
 * @Date 2022/4/1
 * @Version 1.0
 **/
public interface ModelService {
    int createModel(Model model);
    int updateModel(Long modelId, Model model);
    Model queryModel(Long modelId);
    List<Model> queryModelList(int status);
    int deleteModel(Long modelId);
    int uploadModel(Long modelId, MultipartFile model);
    InputStream downloadModel(Long modelId);

}
