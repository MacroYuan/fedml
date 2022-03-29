package com.myproject.fedml.vo;

/**
 * @author MacroYuan
 * @className ExecuteModelParam
 * @Date 2022/3/28
 * @Version 1.0
 **/
public class ExecuteModelParam {
    private int fedratedLearnigngAlgorithmCode;
    private int gpu;

    public int getGpu() {
        return gpu;
    }

    private int modelCode;
    private String modelId;
    private int datasetCode;
    private String datasetId;
    private int clientNum;
    private int workNum;
    private String partitionMethod;
    private int round;
    private int epoch;
    private int batchSize;
    private double lr;
    private String clientOptimizer;
    private int ci;

    public int getFedratedLearnigngAlgorithmCode() {
        return fedratedLearnigngAlgorithmCode;
    }

    public int getModelCode() {
        return modelCode;
    }

    public String getModelId() {
        return modelId;
    }

    public int getDatasetCode() {
        return datasetCode;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public int getClientNum() {
        return clientNum;
    }

    public int getWorkNum() {
        return workNum;
    }

    public String getPartitionMethod() {
        return partitionMethod;
    }

    public int getRound() {
        return round;
    }

    public int getEpoch() {
        return epoch;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public double getLr() {
        return lr;
    }

    public String getClientOptimizer() {
        return clientOptimizer;
    }

    public int getCi() {
        return ci;
    }
}
