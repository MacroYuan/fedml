package com.myproject.fedml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csvreader.CsvReader;
import com.myproject.fedml.mbg.mapper.DatasetMapper;
import com.myproject.fedml.mbg.model.Dataset;
import com.myproject.fedml.service.DatasetService;
import com.myproject.fedml.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author MacroYuan
 * @className DatasetServiceImpl
 * @Date 2022/4/7
 * @Version 1.0
 **/
@Service
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
        // 上传文件需重命名为train_target.csv、train_feature.csv、test_feature.csv、test_target.csv
        String datasetPath = "python/data";
        fileService.setSavePath(datasetPath);
        Dataset dataset = new Dataset();

        fileService.uploadFiles(datasetFile);
        // 存储格式：路径+文件名，eg. /data/dataset/test.csv
        dataset.setDatasetPath(datasetPath + File.separator + datasetFile.getName());

        result = updateDataset(datasetId, dataset);

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

    @Override
    public Map<String, String> getMetaDataset(Long datasetId) throws Exception {
        // TODO: 返回List还是Map
        Map<String, String> result = new HashMap<>();

        // 获取数据集文件路径
        String datasetPath = getDatasetPathById(datasetId);
        if (StringUtils.isEmpty(datasetPath)) {
            return result;
        }

        // TODO: 元数据访问
        // 如果数据集是csv文件格式的标注数据集，怎么读取元数据
        // 如果数据集是图片集形式的数据集，怎么读取元数据

        // 1. 处理csv文件
        String suffixName = datasetPath.contains(".") ? datasetPath.substring(datasetPath.lastIndexOf(".") + 1) : null;
        if (suffixName.equals("csv")) {
            CsvReader reader = new CsvReader(datasetPath, ',', Charset.forName("UTF-8"));
            reader.readRecord();
            String[] headers = reader.getHeaders();
            String[] values = reader.getValues();
            for (String value : values) {
                result.put(value, null);
            }
        } else {
            throw new Exception("目前只支持csv数据集获取元数据");
        }

        // 2. 处理图片集
//        if (suffixName.equals(""))

        return result;
    }


    public static void main(String[] args) {
        // 测试
        String filePath = "D:\\MacroYuan\\Java\\IdeaProjects\\spring-framework\\fedml\\src\\main\\resources\\tempFile\\loan.csv";
        try {
            CsvReader reader = new CsvReader(filePath);
            Boolean isRead = reader.readRecord();
            String[] headers = reader.getHeaders();
            String[] value = reader.getValues();
            List<String> values = Arrays.asList(value);
            System.out.println(values);
            String test =  reader.get(0);
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
