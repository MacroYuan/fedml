package com.myproject.fedml.service;

import com.myproject.fedml.mbg.model.Task;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author MacroYuan
 * @className ModelTaskInfo
 * @Date 2022/3/28
 * @Version 1.0
 **/
public interface ModelTaskInfoService {
    /**
     * 往数据库表中插入task信息
     * @param task
     * @return 1-创建任务成功，0创建失败
     */
    int createTask(Task task);

    /**
     * 更新task信息
     * @param taskId
     * @param task
     * @return
     */
    int updateTask(Long taskId, Task task);

    /**
     * 查询task信息
     * @param taskId
     * @return
     */
    Task queryTask(Long taskId);

    /**
     * 查询所有task
     * @param status
     * @return
     */
    List<Task> queryTaskList(int status);

    /**
     * 删除task
     * @param taskId
     * @return
     */
    int deleteTask(Long taskId);

    /**
     * 获取数据库表中相应taskId的信息，并执行该任务
     * @param taskId
     * @return 执行任务控制台信息
     */
    String executeTask(Long taskId, int idx, int workNum, int usingGpu) throws IOException;

    /**
     * 获取数据库表中相应taskId信息
     * @param taskId
     * @return 任务信息
     */
    int queryTaskStatus(Long taskId);

    public int generateMpiHostFile(Map<String, String> map) throws IOException;

    public int generateGrpcIpConfigFile(Map<String, String> map) throws IOException;

    public int generateGpuMappingFile(Map<String, String> map) throws IOException;
}
