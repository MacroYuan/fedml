package com.myproject.fedml.service.impl;

import com.myproject.fedml.mbg.mapper.DatasetMapper;
import com.myproject.fedml.mbg.model.Dataset;
import com.myproject.fedml.service.DatasetService;
import com.myproject.fedml.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;

/**
 * @author MacroYuan
 * @className DatasetServiceImpl
 * @Date 2022/4/7
 * @Version 1.0
 **/
public class DatasetServiceImpl implements DatasetService {

    @Resource
    private DatasetMapper datasetMapper;

    @Resource
    private FileService fileService;

    @Override
    public int createDataset(Dataset dataset) {
        int result = 0;
        result = datasetMapper.insert(dataset);
        return result;
    }

    @Override
    public int uploadDataset(Long datasetId, MultipartFile datasetFile) {
        int result = 0;
        Dataset dataset = datasetMapper.selectById(datasetId);
        dataset.setDatasetPath(fileService.uploadFiles(datasetFile));
        return result;
    }

    @Override
    public InputStream downloadDataset(Long datasetId) {
        Dataset dataset = datasetMapper.selectById(datasetId);
        return fileService.downloadFiles(dataset.getDatasetPath());
    }

    @Override
    public String getDatasetPathById(Long datasetId) {
        String result = null;
        result = datasetMapper.selectById(datasetId).getDatasetPath();
        return result;
    }
}
