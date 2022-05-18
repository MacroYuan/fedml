package com.myproject.fedml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.fedml.common.DatasetEnum;
import com.myproject.fedml.common.FederatedLearningAlgorithmEnum;
import com.myproject.fedml.common.ModelEnum;
import com.myproject.fedml.common.OptimizerEnum;
import com.myproject.fedml.mbg.mapper.TaskMapper;
import com.myproject.fedml.mbg.model.Dataset;
import com.myproject.fedml.mbg.model.Model;
import com.myproject.fedml.mbg.model.Task;
import com.myproject.fedml.service.DatasetService;
import com.myproject.fedml.service.ModelService;
import com.myproject.fedml.service.ModelTaskInfoService;
import com.myproject.fedml.utils.DistributeFile;
import com.myproject.fedml.utils.LinuxCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author MacroYuan
 * @className ModelTaskInfoServiceImpl
 * @Date 2022/3/30
 * @Version 1.0
 **/
@Service("modelTaskInfoService")
public class ModelTaskInfoServiceImpl implements ModelTaskInfoService {

    @Value("${file.task-file-path}")
    private String taskFilePath;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private DatasetService datasetService;

    @Resource
    private ModelService modelService;

    @Override
    public int createTask(Task task) {
        int result;
        try {
//            // 构建执行脚本command
//            String command = null;
//
//            // 算法执行脚本
//            String shellScript = null;
//            switch (task.getFederatedLearningAlgorithmCode()) {
//                case 1:
//                    // fedavg
//                    shellScript = "";
//                    command = "sh " + shellScript
//                            + task.getClientNum() + " "
//                            + task.getWorkerNum() + " "
//                            + ModelEnum.getModelNameByCode(Math.toIntExact(task.getModelId())) + " "
//                            + DatasetEnum.getDatasetNameByCode(Math.toIntExact(task.getDatasetId())) + " "
//                            + DatasetEnum.getDatasetPathByCode(Math.toIntExact(task.getDatasetId())) + " "
//                            + task.getPartitionMethod() + " ";
////                            + task.getCommRound() + " "
////                            + task.getEpochs() + " "
////                            + task.batchSize() + " "
////                            + task.getLearningRate() + " "
////                            + task.getClientOptimizer() + " "
////                            + task.getBackend() + " "
////                            + task.getCI() + " "
////                            + task.getFeatureName() + " "
////                            + task.getTargetName() + " ";
//                    break;
//                case 2:
//                    // vfl
//                    shellScript = "";
//                    command = "sh " + shellScript
//                            + task.getClientNum() + " "
//                            + DatasetEnum.getDatasetNameByCode(Math.toIntExact(task.getDatasetId())) + " ";
////                            + task.getCommRound() + " "
////                            + task.batchSize() + " "
////                            + task.getLearningRate() + " ";
//
//                    break;
//                default:
//                    throw new Exception("未识别联邦算法Code");
//            }
//
//
//            task.setCommand(command);
            result = taskMapper.insert(task);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }


//
//    @Override
//    public int generateMqttConfigPath() throws IOException {
//        int result = 0;
//
//        return result;
//    }

    @Override
    public String executeTask(Long taskId, int idx, int workNum, int usingGpu) throws IOException {
        String result;

        // TODO：执行前更新状态
        Task updateTaskBefore = new Task();
        updateTaskBefore.setStatus(2);
        updateTask(taskId, updateTaskBefore);

        Task task = taskMapper.selectById(taskId);
        String filePath = taskFilePath;
        buildConfig(filePath, task, workNum, usingGpu);
        String command = null;
        // TODO:
        if (idx == 1) {
            command = "python /python/examples/simulation/sp_fedavg_mnist_lr_example/torch_fedavg_mnist_lr_custum_data_and_model_example.py " + "--cf fedml_config.yaml";
        } else {
            command = "python /python/examples/simulation/sp_fedavg_mnist_lr_example/torch_fedavg_mnist_lr_custum_data_and_model_example.py " + "--cf fedml_config.yaml";
//            command = "bash run_client.sh " + idx;
        }
        result = LinuxCommand.run(command);
        // TODO：执行完更新状态
        Task updateTaskAfter = new Task();
        updateTaskAfter.setStatus(3);
        updateTask(taskId, updateTaskAfter);

        return result;
    }

    @Override
    public int queryTaskStatus(Long taskId) {
        int result = 0;
        try {
            Task task = taskMapper.selectById(taskId);
            result = task.getStatus();
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    @Override
    public int updateTask(Long taskId, Task task) {
        int result = 0;
        int updateTime = (int) (System.currentTimeMillis() / 1000);
        task.setTaskId(taskId);
        task.setUpdateTime(updateTime);
        result = taskMapper.updateById(task);
        return result;
    }

    @Override
    public Task queryTask(Long taskId) {
        Task result = null;
        result = taskMapper.selectById(taskId);
        return result;
    }

    @Override
    public List<Task> queryTaskList(int status) {
        List<Task> result = null;
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        result = taskMapper.selectList(wrapper.eq("status", status));
        return result;
    }

    @Override
    public int deleteTask(Long taskId) {
        int result = 0;
        result = taskMapper.deleteById(taskId);
        return result;
    }

    @Override
    public int generateMpiHostFile(Map<String, String> map) throws IOException {
        int result = 0;

        String distributeFilePath = taskFilePath;
        File newFile = DistributeFile.generateMpiHostFile(distributeFilePath, map);

        return result;
    }

    @Override
    public int generateGrpcIpConfigFile(Map<String, String> map) throws IOException {
        int result = 0;

        String filePath = taskFilePath;
        File newFile = DistributeFile.generateGrpcIpConfigFile(filePath, map);

        return result;
    }

    @Override
    public int generateGpuMappingFile(Map<String, String> map) throws IOException {
        int result = 0;

        String filePath = "/fedml/python/examples/config";
        File newFile = DistributeFile.generateGpuMappingFile(filePath, map);

        return result;
    }

    private void buildConfig(String filePath, Task task, int workNum, int usingGpu) throws IOException {
        String fileName = "fedml_config.yaml";

        String src = filePath + File.pathSeparator + fileName;

        Yaml yaml = new Yaml();
        FileWriter fileWriter = null;

        Map<String, Object> resultMap, datasetMap, modelMap, trainMap, deviceMap;

        try {
            // 读取yaml文件，默认返回根目录结构
            resultMap = (Map<String, Object>) yaml.load(new FileInputStream(new File(src)));

            //
            datasetMap = (Map<String, Object>) resultMap.get("data_args");
            datasetMap.put("dataset", DatasetEnum.getDatasetNameByCode(Math.toIntExact(task.getDatasetId())));
            Dataset dataset = datasetService.queryDataset(task.getDatasetId());
            datasetMap.put("data_cache_dir", dataset.getDatasetPath());

            //
            modelMap = (Map<String, Object>) resultMap.get("model_args");
            modelMap.put("model", ModelEnum.getModelNameByCode(Math.toIntExact(task.getModelId())));
            Model model = modelService.queryModel(task.getModelId());

            //
            trainMap = (Map<String, Object>) resultMap.get("train_args");
            trainMap.put("federated_optimizer", FederatedLearningAlgorithmEnum.getFederatedNameByCode(task.getFederatedOptimizer()));
            trainMap.put("client_id_list", task.getClientList());
            trainMap.put("client_num_in_total", task.getClientNumTotal());
            trainMap.put("client_num_per_round", task.getClientNumPerRound());
            trainMap.put("comm_round", task.getCommRound());
            trainMap.put("epochs", task.getEpochs());
            trainMap.put("batch_size", task.getBatchSize());
            trainMap.put("client_optimizer", OptimizerEnum.getOptimizerNameByCode(task.getClientOptimizer()));
            trainMap.put("learning_rate", task.getLearningRate());
            trainMap.put("weight_decay", task.getWeightDecay());

            //
            deviceMap = (Map<String, Object>) resultMap.get("device_args");
            deviceMap.put("worker_num", workNum);
            deviceMap.put("usingGpu", usingGpu);

            fileWriter = new FileWriter(new File(src));
            fileWriter.write(yaml.dumpAsMap(resultMap));
            fileWriter.flush();
        } finally {
            fileWriter.close();
        }
    }
}
