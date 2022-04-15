package com.myproject.fedml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.fedml.mbg.mapper.ModelMapper;
import com.myproject.fedml.mbg.model.Model;
import com.myproject.fedml.service.FileService;
import com.myproject.fedml.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author MacroYuan
 * @className ModelServiceImpl
 * @Date 2022/4/7
 * @Version 1.0
 **/
@Service
public class ModelServiceImpl implements ModelService {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private FileService fileService;

    @Override
    public int createModel(Model model) {
        int result = 0;

        int createTime = (int) (System.currentTimeMillis() / 1000);
        int updateTime = (int) (System.currentTimeMillis() / 1000);
        int status = 0;

        model.setCreateTime(createTime);
        model.setUpdateTime(updateTime);
        model.setStatus(status);

        result = modelMapper.insert(model);
        return result;
    }

    @Override
    public int updateModel(Long modelId, Model model) {
        int result = 0;
        // 记录每次更新的时间
        int updateTime = (int) (System.currentTimeMillis() / 1000);
        model.setModelId(modelId);
        model.setUpdateTime(updateTime);
        result = modelMapper.updateById(model);
        return result;
    }

    @Override
    public Model queryModel(Long modelId) {
        Model result = null;
        result = modelMapper.selectById(modelId);
        return result;
    }

    @Override
    public List<Model> queryModelList(int status) {
        List<Model> result = null;

        QueryWrapper<Model> wrapper = new QueryWrapper<>();

        result = modelMapper.selectList(wrapper.eq("status", status));

        return result;
    }

    @Override
    public int deleteModel(Long modelId) {
        int result = 0;

        result = modelMapper.deleteById(modelId);

        return result;
    }

    @Override
    public int uploadModel(Long modelId, MultipartFile modelFile) {
        int result = 0;
        Model model = new Model();
        model.setModelPath(fileService.uploadFiles(modelFile));
        // 更新模型表的模型路径
        result = updateModel(modelId, model);
        return result;
    }

    @Override
    public InputStream downloadModel(Long modelId) {
        Model model = modelMapper.selectById(modelId);
        return fileService.downloadFiles(model.getModelPath());
    }
}
