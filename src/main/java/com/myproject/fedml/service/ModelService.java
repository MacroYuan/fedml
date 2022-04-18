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
    /**
     *
     * @param model
     * @return
     */
    int createModel(Model model);

    /**
     *
     * @param modelId
     * @param model
     * @return
     */
    int updateModel(Long modelId, Model model);

    /**
     *
     * @param modelId
     * @return
     */
    Model queryModel(Long modelId);

    /**
     *
     * @param status
     * @return
     */
    List<Model> queryModelList(int status);

    /**
     *
     * @param modelId
     * @return
     */
    int deleteModel(Long modelId);

    /**
     *
     * @param modelId
     * @param model
     * @return
     */
    int uploadModel(Long modelId, MultipartFile model);

    /**
     *
     * @param modelId
     * @return
     */
    InputStream downloadModel(Long modelId);

}
