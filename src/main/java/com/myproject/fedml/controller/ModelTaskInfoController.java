package com.myproject.fedml.controller;

import com.myproject.fedml.common.DatasetEnum;
import com.myproject.fedml.common.ModelEnum;
import com.myproject.fedml.common.utils.Result;
import com.myproject.fedml.utils.LinuxCommand;
import com.myproject.fedml.vo.ExecuteModelParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author MacroYuan
 * @className ModelTaskInfoController
 * @Date 2022/3/28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/modelTaskInfo")
public class ModelTaskInfoController {

    /**
     * 简单测试服务运行
     * @return Result
     */
    @RequestMapping("runModel")
    public Result runModel(@RequestBody ExecuteModelParam executeModelParam) {
        try {
            // 参数校验
        } catch (Exception e) {
            return Result.error();
        }

        try {
            String command = "sh ../../../resources/static/shell/test.sh "
                    + executeModelParam.getGpu() + " "
                    + executeModelParam.getClientNum() + " "
                    + executeModelParam.getWorkNum() + " "
                    + executeModelParam.getBatchSize() + " "
                    + DatasetEnum.getDatasetNameByCode(executeModelParam.getDatasetCode()) + " "
                    + DatasetEnum.getDatasetPathByCode(executeModelParam.getDatasetCode()) + " "
                    + ModelEnum.getModelNameByCode(executeModelParam.getModelCode()) + " "
                    + executeModelParam.getPartitionMethod() + " "
                    + executeModelParam.getRound() + " "
                    + executeModelParam.getEpoch() + " "
                    + executeModelParam.getLr() + " "
                    + executeModelParam.getClientOptimizer() + " "
                    + executeModelParam.getCi();

            String result = LinuxCommand.run(command);
            System.out.println(result);
        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok();
    }

    @RequestMapping("/hello")
    public Result sayHello() {
        return Result.ok();
    }
}
