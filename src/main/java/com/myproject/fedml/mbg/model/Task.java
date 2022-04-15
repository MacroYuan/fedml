package com.myproject.fedml.mbg.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Task implements Serializable {
    @TableId("task_id")
    private Long taskId;

    private Long modelId;

    private String modelPath;

    private Long datasetId;

    private String datasetPath;

    private Integer datasetProcess;

    private String datasetProcessFile;

    private String hostFile;

    private Integer gpu;

    private String gpuMappingFile;

    private String command;

    private Integer createTime;

    private Integer beginTime;

    private Integer endTime;

    private String description;

    private Integer clientNum;

    private Integer workerNum;

    private String partitionMethod;

    private Integer deleteflag;

    private String extra;

    private Integer updateTime;

    private static final long serialVersionUID = 1L;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetPath() {
        return datasetPath;
    }

    public void setDatasetPath(String datasetPath) {
        this.datasetPath = datasetPath;
    }

    public Integer getDatasetProcess() {
        return datasetProcess;
    }

    public void setDatasetProcess(Integer datasetProcess) {
        this.datasetProcess = datasetProcess;
    }

    public String getDatasetProcessFile() {
        return datasetProcessFile;
    }

    public void setDatasetProcessFile(String datasetProcessFile) {
        this.datasetProcessFile = datasetProcessFile;
    }

    public String getHostFile() {
        return hostFile;
    }

    public void setHostFile(String hostFile) {
        this.hostFile = hostFile;
    }

    public Integer getGpu() {
        return gpu;
    }

    public void setGpu(Integer gpu) {
        this.gpu = gpu;
    }

    public String getGpuMappingFile() {
        return gpuMappingFile;
    }

    public void setGpuMappingFile(String gpuMappingFile) {
        this.gpuMappingFile = gpuMappingFile;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClientNum() {
        return clientNum;
    }

    public void setClientNum(Integer clientNum) {
        this.clientNum = clientNum;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public String getPartitionMethod() {
        return partitionMethod;
    }

    public void setPartitionMethod(String partitionMethod) {
        this.partitionMethod = partitionMethod;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", modelId=").append(modelId);
        sb.append(", modelPath=").append(modelPath);
        sb.append(", datasetId=").append(datasetId);
        sb.append(", datasetPath=").append(datasetPath);
        sb.append(", datasetProcess=").append(datasetProcess);
        sb.append(", datasetProcessFile=").append(datasetProcessFile);
        sb.append(", hostFile=").append(hostFile);
        sb.append(", gpu=").append(gpu);
        sb.append(", gpuMappingFile=").append(gpuMappingFile);
        sb.append(", command=").append(command);
        sb.append(", createTime=").append(createTime);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", description=").append(description);
        sb.append(", clientNum=").append(clientNum);
        sb.append(", workerNum=").append(workerNum);
        sb.append(", partitionMethod=").append(partitionMethod);
        sb.append(", deleteflag=").append(deleteflag);
        sb.append(", extra=").append(extra);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}