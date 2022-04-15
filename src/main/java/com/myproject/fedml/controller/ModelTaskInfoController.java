package com.myproject.fedml.controller;

import com.myproject.fedml.common.DatasetEnum;
import com.myproject.fedml.common.ModelEnum;
import com.myproject.fedml.common.utils.Result;
import com.myproject.fedml.mbg.mapper.UserMapper;
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

import static org.slf4j.LoggerFactory.getLogger;


/**
 * @author MacroYuan
 * @className ModelTaskInfoController
 * @Date 2022/3/28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/modelTaskInfo/task")
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
            // 参数校验
            System.out.println(executeTaskParam.toString());
        } catch (Exception e) {
            logger.error("");
            return Result.error();
        }

        try {
            String command = "sh ../../../resources/static/shell/test.sh "
                    + executeTaskParam.getGpu() + " "
                    + executeTaskParam.getClientNum() + " "
                    + executeTaskParam.getWorkNum() + " "
                    + executeTaskParam.getBatchSize() + " "
                    + DatasetEnum.getDatasetNameByCode(executeTaskParam.getDatasetCode()) + " "
                    + DatasetEnum.getDatasetPathByCode(executeTaskParam.getDatasetCode()) + " "
                    + ModelEnum.getModelNameByCode(executeTaskParam.getModelCode()) + " "
                    + executeTaskParam.getPartitionMethod() + " "
                    + executeTaskParam.getRound() + " "
                    + executeTaskParam.getEpoch() + " "
                    + executeTaskParam.getLr() + " "
                    + executeTaskParam.getClientOptimizer() + " "
                    + executeTaskParam.getCi();

            String result = LinuxCommand.run(command);
            System.out.println(result);
        } catch (Exception e) {
            logger.error("");
            return Result.error();
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
            int taskId = Integer.parseInt(request.getParameter("taskId"));
        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result sayHello() {
        return Result.ok();
    }
}
