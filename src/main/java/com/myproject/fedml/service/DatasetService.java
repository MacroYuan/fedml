package com.myproject.fedml.service;

import com.myproject.fedml.mbg.model.Dataset;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author MacroYuan
 * @className DatasetService
 * @Date 2022/4/1
 * @Version 1.0
 **/
public interface DatasetService {

    /**
     * 创建数据集
     * @param dataset
     * @return
     */
    int createDataset(Dataset dataset);

    /**
     * 更新数据集信息
     * @param datasetId
     * @param dataset
     * @return
     */
    int updateDataset(Long datasetId, Dataset dataset);

    /**
     * 查询数据集
     * @param datasetId
     * @return
     */
    Dataset queryDataset(Long datasetId);

    /**
     * 查询数据集信息
     * @param status
     * @return
     */
    List<Dataset> queryDatasetList(int status);

    /**
     * 删除数据集信息
     * @param datasetId
     * @return
     */
    int deleteDataset(Long datasetId);

    /**
     * 上传数据集文件
     * @param datasetId
     * @param datasetFile
     * @return
     */
    int uploadDataset(Long datasetId, MultipartFile datasetFile);

    /**
     * 下载数据集
     * @param datasetId
     * @return
     */
    InputStream downloadDataset(Long datasetId);

    /**
     * 根据数据集Id获取数据集存储路径
     * @param datasetId
     * @return
     */
    String getDatasetPathById(Long datasetId);

    /**
     * 数据集元数据查看
     */
    Map<String, String> getMetaDataset(Long datasetId) throws Exception;
}
