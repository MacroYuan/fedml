package com.myproject.fedml.vo;

import lombok.Getter;

/**
 * @author MacroYuan
 * @className ExecuteModelParam
 * @Date 2022/3/28
 * @Version 1.0
 **/
@Getter
public class ExecuteTaskParam {
    private int taskId;
    private int federatedLearningAlgorithmCode;
    private int datasetCode;
//    private String datasetId;
    private int modelCode;
//    private String modelId;
    private int gpu;
    private int clientNum;
    private int workNum;
    private String partitionMethod;
    private int round;
    private int epoch;
    private int batchSize;
    private double lr;
    private String clientOptimizer;
    private int ci;
}
