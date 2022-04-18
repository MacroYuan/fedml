package com.myproject.fedml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.fedml.mbg.mapper.DatasetMapper;
import com.myproject.fedml.mbg.model.Dataset;
import com.myproject.fedml.service.DatasetService;
import com.myproject.fedml.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.List;

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

        int createTime = (int) (System.currentTimeMillis() / 1000);
        int updateTime = (int) (System.currentTimeMillis() / 1000);
        int status = 0;

        dataset.setCreateTime(createTime);
        dataset.setUpdateTime(updateTime);
        dataset.setStatus(status);

        result = datasetMapper.insert(dataset);
        return result;
    }

    @Override
    public int updateDataset(Long datasetId, Dataset dataset) {
        int result = 0;

        int updateTime = (int) (System.currentTimeMillis() / 1000);
        dataset.setDatasetId(datasetId);
        dataset.setUpdateTime(updateTime);

        result = datasetMapper.updateById(dataset);

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
    public Dataset queryDataset(Long datasetId) {
        Dataset result = null;

        result = datasetMapper.selectById(datasetId);

        return result;
    }

    @Override
    public List<Dataset> queryDatasetList(int status) {
        List<Dataset> result = null;

        QueryWrapper<Dataset> wrapper = new QueryWrapper<>();

        result = datasetMapper.selectList(wrapper.eq("status", status));

        return result;
    }

    @Override
    public int deleteDataset(Long datasetId) {
        int result = 0;

        result = datasetMapper.deleteById(datasetId);

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
