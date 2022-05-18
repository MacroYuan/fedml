package com.myproject.fedml.mbg.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Task implements Serializable {
    @TableId("task_id")
    private Long taskId;

    private Long datasetId;

    private Long modelId;

    /**
     * 1-FedAvg
     *
     * @mbggenerated
     */
    private Integer federatedOptimizer;

    private String clientList;

    private Integer clientNumTotal;

    private Integer clientNumPerRound;

    private Integer commRound;

    private Integer epochs;

    private Integer batchSize;

    /**
     * 1-SGD
     *
     * @mbggenerated
     */
    private Integer clientOptimizer;

    private Float learningRate;

    private Float weightDecay;

    private Integer workerNum;

    /**
     * 0-使用CPU
1-使用GPU
     *
     * @mbggenerated
     */
    private Integer usingGpu;

    /**
     * 1-单机模拟
2-分布式
     *
     * @mbggenerated
     */
    private Integer backend;

    private String description;

    private Integer deleteFlag;

    private Integer beginTime;

    private Integer endTime;

    private Integer createTime;

    private Integer updateTime;

    private Integer status;

    private String extra;

    private static final long serialVersionUID = 1L;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Integer getFederatedOptimizer() {
        return federatedOptimizer;
    }

    public void setFederatedOptimizer(Integer federatedOptimizer) {
        this.federatedOptimizer = federatedOptimizer;
    }

    public String getClientList() {
        return clientList;
    }

    public void setClientList(String clientList) {
        this.clientList = clientList;
    }

    public Integer getClientNumTotal() {
        return clientNumTotal;
    }

    public void setClientNumTotal(Integer clientNumTotal) {
        this.clientNumTotal = clientNumTotal;
    }

    public Integer getClientNumPerRound() {
        return clientNumPerRound;
    }

    public void setClientNumPerRound(Integer clientNumPerRound) {
        this.clientNumPerRound = clientNumPerRound;
    }

    public Integer getCommRound() {
        return commRound;
    }

    public void setCommRound(Integer commRound) {
        this.commRound = commRound;
    }

    public Integer getEpochs() {
        return epochs;
    }

    public void setEpochs(Integer epochs) {
        this.epochs = epochs;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getClientOptimizer() {
        return clientOptimizer;
    }

    public void setClientOptimizer(Integer clientOptimizer) {
        this.clientOptimizer = clientOptimizer;
    }

    public Float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(Float learningRate) {
        this.learningRate = learningRate;
    }

    public Float getWeightDecay() {
        return weightDecay;
    }

    public void setWeightDecay(Float weightDecay) {
        this.weightDecay = weightDecay;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public Integer getUsingGpu() {
        return usingGpu;
    }

    public void setUsingGpu(Integer usingGpu) {
        this.usingGpu = usingGpu;
    }

    public Integer getBackend() {
        return backend;
    }

    public void setBackend(Integer backend) {
        this.backend = backend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
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
        sb.append(", taskId=").append(taskId);
        sb.append(", datasetId=").append(datasetId);
        sb.append(", modelId=").append(modelId);
        sb.append(", federatedOptimizer=").append(federatedOptimizer);
        sb.append(", clientList=").append(clientList);
        sb.append(", clientNumTotal=").append(clientNumTotal);
        sb.append(", clientNumPerRound=").append(clientNumPerRound);
        sb.append(", commRound=").append(commRound);
        sb.append(", epochs=").append(epochs);
        sb.append(", batchSize=").append(batchSize);
        sb.append(", clientOptimizer=").append(clientOptimizer);
        sb.append(", learningRate=").append(learningRate);
        sb.append(", weightDecay=").append(weightDecay);
        sb.append(", workerNum=").append(workerNum);
        sb.append(", usingGpu=").append(usingGpu);
        sb.append(", backend=").append(backend);
        sb.append(", description=").append(description);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", extra=").append(extra);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}