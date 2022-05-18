package com.myproject.fedml.controller;

import com.myproject.fedml.common.DatasetEnum;
import com.myproject.fedml.common.ModelEnum;
import com.myproject.fedml.common.utils.Result;
import com.myproject.fedml.mbg.mapper.UserMapper;
import com.myproject.fedml.mbg.model.Task;
import com.myproject.fedml.service.ModelTaskInfoService;
import com.myproject.fedml.utils.LinuxCommand;
import com.myproject.fedml.vo.ExecuteTaskParam;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * @author MacroYuan
 * @className ModelTaskInfoController
 * @Date 2022/3/28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/task")
public class ModelTaskInfoController {
    private static final Logger logger = getLogger(ModelTaskInfoController.class);

    @Autowired
    private ModelTaskInfoService modelTaskInfoService;

    /**
     * 创建任务接口
     * @return Result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result createTask(@RequestBody ExecuteTaskParam executeTaskParam) {

        try {
            Task newTask = executeTaskParam.toTaskCreate();

            modelTaskInfoService.createTask(newTask);
        } catch (Exception e) {
            logger.error("");
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public Result run(HttpServletRequest request) {
        try {
            Long taskId = Long.valueOf(request.getParameter("taskId"));
            Integer idx = Integer.valueOf(request.getParameter("idx"));
            Integer workerNum = Integer.valueOf(request.getParameter("workNum"));
            Integer usingGpu = Integer.valueOf(request.getParameter("usingGpu"));

            modelTaskInfoService.executeTask(taskId, idx, workerNum, usingGpu);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Result queryStatus(HttpServletRequest request) {
        Map<String, String> data = new HashMap<>();
        try {
            Long taskId = Long.valueOf(request.getParameter("taskId"));
            int status = modelTaskInfoService.queryTaskStatus(taskId);
            data.put("status", String.valueOf(status));
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok().put("data", data);
    }

    @RequestMapping(value = "/generate/gpuMapingFile", method = RequestMethod.POST)
    public Result generateGpuMappingFile(@RequestBody Map<String, String> map) {
        try {
            modelTaskInfoService.generateGpuMappingFile(map);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/generate/mpiHostFile", method = RequestMethod.POST)
    public Result generateMpiHostFile(@RequestBody Map<String, String> map) {
        try {
            modelTaskInfoService.generateMpiHostFile(map);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/generate/grpcIpConfigFile", method = RequestMethod.POST)
    public Result generateGrpcIpConfigFile(@RequestBody Map<String, String> map) {
        try {
            modelTaskInfoService.generateGrpcIpConfigFile(map);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result sayHello() {
        return Result.ok();
    }
}
