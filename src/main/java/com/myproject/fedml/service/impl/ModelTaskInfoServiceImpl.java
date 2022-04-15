package com.myproject.fedml.service.impl;

import com.myproject.fedml.mbg.mapper.TaskMapper;
import com.myproject.fedml.mbg.model.Task;
import com.myproject.fedml.service.ModelTaskInfoService;
import com.myproject.fedml.utils.LinuxCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author MacroYuan
 * @className ModelTaskInfoServiceImpl
 * @Date 2022/3/30
 * @Version 1.0
 **/
@Service("modelTaskInfoService")
public class ModelTaskInfoServiceImpl implements ModelTaskInfoService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public int createTask(Task task) {
        int result;
        try {
            result = taskMapper.insert(task);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    @Override
    public String executeTask(Long taskId) {
        String result;

        try {
            Task task = taskMapper.selectById(taskId);
            String command = task.getCommand();
            result = LinuxCommand.run(command);
        } catch (IOException e) {
            return null;
        }
        return result;
    }

    @Override
    public String queryTaskStatus(Long taskId) {
        String result;
        try {
            Task task = taskMapper.selectById(taskId);
            result = task.toString();
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
