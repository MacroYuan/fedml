package com.myproject.fedml.service;

import com.myproject.fedml.mbg.model.Task;

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
     * 获取数据库表中相应taskId的信息，并执行该任务
     * @param taskId
     * @return 执行任务控制台信息
     */
    String executeTask(Long taskId);

    /**
     * 获取数据库表中相应taskId信息
     * @param taskId
     * @return 任务信息
     */
    String queryTaskStatus(Long taskId);
}
