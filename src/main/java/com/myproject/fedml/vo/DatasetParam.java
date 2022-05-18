package com.myproject.fedml.vo;

import com.myproject.fedml.mbg.model.Dataset;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author MacroYuan
 * @className DatasetParam
 * @Date 2022/4/25
 * @Version 1.0
 **/
@Getter
public class DatasetParam {
    private Long datasetId;
    private String datasetName;
    private String datasetDescription;
    private String datasetPath;
    private String extra;

    public Dataset toDatasetCreate() throws Exception {

        // 参数校验
        if (this.datasetId == 0L) {
            throw new Exception("数据集id错误");
        } else if (StringUtils.isEmpty(datasetName)) {
            throw new Exception("数据集名不能为空");
        }

        Dataset result = new Dataset();

        // 设置参数
        result.setDatasetId(datasetId);
        result.setDatasetName(datasetName);
        if (!StringUtils.isEmpty(datasetDescription)) {
            result.setDatasetDescription(datasetDescription);
        }
        if (!StringUtils.isEmpty(datasetPath)) {
            result.setDatasetPath(datasetPath);
        }
        if (!StringUtils.isEmpty(extra)) {
            result.setExtra(extra);
        }

        return result;

    }

    public Dataset toDatasetUpdate() throws Exception {
        Dataset result = new Dataset();

        // 设置参数
        if (!StringUtils.isEmpty(datasetName)) {
            result.setDatasetName(datasetName);
        }
        if (!StringUtils.isEmpty(datasetDescription)) {
            result.setDatasetDescription(datasetDescription);
        }
        if (!StringUtils.isEmpty(datasetPath)) {
            result.setDatasetPath(datasetPath);
        }
        if (!StringUtils.isEmpty(extra)) {
            result.setExtra(extra);
        }

        return result;
    }
}