package com.myproject.fedml.mbg.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Dataset implements Serializable {
    @TableId("dataset_id")
    private Long datasetId;

    private String datasetName;

    private String datasetPath;

    private String datasetDescriptiom;

    private Integer createTime;

    private Integer updateTime;

    private Integer status;

    private String extra;

    private static final long serialVersionUID = 1L;

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getDatasetPath() {
        return datasetPath;
    }

    public void setDatasetPath(String datasetPath) {
        this.datasetPath = datasetPath;
    }

    public String getDatasetDescriptiom() {
        return datasetDescriptiom;
    }

    public void setDatasetDescriptiom(String datasetDescriptiom) {
        this.datasetDescriptiom = datasetDescriptiom;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", datasetId=").append(datasetId);
        sb.append(", datasetName=").append(datasetName);
        sb.append(", datasetPath=").append(datasetPath);
        sb.append(", datasetDescriptiom=").append(datasetDescriptiom);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", extra=").append(extra);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}