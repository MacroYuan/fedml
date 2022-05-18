package com.myproject.fedml.vo;

import com.myproject.fedml.mbg.model.Task;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author MacroYuan
 * @className ExecuteModelParam
 * @Date 2022/3/28
 * @Version 1.0
 **/
@Getter
public class ExecuteTaskParam {
    private Long taskId;
    private Long modelId;
    private Long datasetId;
    private Integer federatedOptimizer;
    private String clientIdList;
    private int clientNumInTotal;
    private int clientNumPerRound;
    private int commRound;
    private int epochs;
    private int batchSize;
    private int clientOptimizer;
    private float learningRate;
    private float weightDecay;
    private int workerNum;
    private int usingGpu;
    private int backend;
    private int status;


    public Task toTaskCreate() throws Exception {
        Task result = new Task();

        if (this.taskId == 0L) {
            throw new Exception("任务id错误");
        } else if (this.modelId == 0L) {
            throw new Exception("模型id错误");
        } else if (this.datasetId == 0L) {
            throw new Exception("数据集id错误");
        }

        result.setTaskId(taskId);
        result.setModelId(modelId);
        result.setDatasetId(datasetId);
        if (federatedOptimizer == 0L) {
            throw new Exception("联邦算法选择错误");
        }
        result.setFederatedOptimizer(federatedOptimizer);

        if (!StringUtils.isEmpty(clientIdList)) {
            result.setClientList(clientIdList);
        }

        if (clientNumInTotal == 0 || clientNumPerRound ==0 || commRound <= 0) {
            throw new Exception("客户端数量设置错误");
        }
        result.setClientNumTotal(clientNumInTotal);
        result.setClientNumPerRound(clientNumPerRound);
        result.setCommRound(commRound);

        if (epochs <= 0 || batchSize <= 0 || learningRate < 0 || weightDecay < 0) {
            throw new Exception("训练参数设置错误");
        }
        result.setEpochs(epochs);
        result.setBatchSize(batchSize);
        result.setClientOptimizer(clientOptimizer);
        result.setLearningRate(learningRate);
        result.setWeightDecay(weightDecay);

        if (workerNum <= 0 || (usingGpu != 1 && usingGpu != 0)) {
            throw new Exception("客户端参数设置错误");
        }
        result.setWorkerNum(workerNum);
        result.setUsingGpu(usingGpu);

        if (backend != 1 && backend != 2) {
            throw new Exception("通信参数设置错误");
        }
        result.setBackend(backend);

        return result;

    }

    public Task toTaskUpdate() throws Exception {
        // 目前只会更改状态
        Task result = new Task();
        result.setStatus(status);

        return result;
    }
}
