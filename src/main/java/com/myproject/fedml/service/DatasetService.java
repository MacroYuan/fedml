package com.myproject.fedml.service;

import com.myproject.fedml.mbg.model.Dataset;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author MacroYuan
 * @className DatasetService
 * @Date 2022/4/1
 * @Version 1.0
 **/
public interface DatasetService {

    int createDataset(Dataset dataset);

    int uploadDataset(Long datasetId, MultipartFile datasetFile);

    InputStream downloadDataset(Long datasetId);

    String getDatasetPathById(Long datasetId);
}
