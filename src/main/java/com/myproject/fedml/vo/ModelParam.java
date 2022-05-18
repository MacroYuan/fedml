package com.myproject.fedml.vo;

import com.myproject.fedml.mbg.model.Model;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author MacroYuan
 * @className ModelParam
 * @Date 2022/4/7
 * @Version 1.0
 **/
@Getter
public class ModelParam {
    private Long modelId;
    private String modelName;
    private String modelDescription;
    private String modelPath;
    private String extra;

    public Model toModelCreate() throws Exception {

        // 参数校验
        if (this.modelId == 0L) {
            throw new Exception("模型id错误");
        } else if (StringUtils.isEmpty(modelName)) {
            throw new Exception("模型名错误不能为空");
        }
        Model result = new Model();

        // 设置参数
        result.setModelId(modelId);
        result.setModelName(modelName);
        if (!StringUtils.isEmpty(modelDescription)) {
            result.setModelDescription(modelDescription);
        }
        if (!StringUtils.isEmpty(modelPath)) {
            result.setModelPath(modelPath);
        }
        if (!StringUtils.isEmpty(extra)) {
            result.setExtra(extra);
        }
        return result;
    }

    public Model toModelUpdate() {
        // 除ID不可更改，其他可更改
        Model result = new Model();

        if (!StringUtils.isEmpty(modelName)) {
            result.setModelName(modelName);
        }
        if (!StringUtils.isEmpty(modelPath)) {
            result.setModelPath(modelPath);
        }
        if (!StringUtils.isEmpty(modelDescription)) {
            result.setModelDescription(modelDescription);
        }
        if (!StringUtils.isEmpty(extra)) {
            result.setExtra(extra);
        }

        return result;
    }
}
