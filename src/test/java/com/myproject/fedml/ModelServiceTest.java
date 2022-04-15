package com.myproject.fedml;

import com.myproject.fedml.mbg.model.Model;
import com.myproject.fedml.service.ModelService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MacroYuan
 * @className ModelServiceTest
 * @Date 2022/4/15
 * @Version 1.0
 **/
@SpringBootTest
public class ModelServiceTest {

    @Resource
    private ModelService modelService;

    @Test
    public void testModelInsert() {
        Model model = new Model();
        model.setModelId(20L);
        model.setModelName("Test");
        model.setModelPath("");
        model.setExtra("");

        int result = modelService.createModel(model);
        System.out.println(result);
    }

    @Test
    public void testModelUpdate() {
        Model model = new Model();
        Long modelId = 11L;
        model.setModelDescription("this is a test");
        int result = modelService.updateModel(modelId, model);
        System.out.println(result);
    }

    @Test
    public void testModelDelete() {
        Long modelId = 11L;
        int result = modelService.deleteModel(modelId);
        System.out.println(result);
    }

    @Test
    public void testModelQuery() {
        Model result = null;
        result = modelService.queryModel(10L);
        System.out.println(result.toString());
    }

    @Test
    public void testModelQueryList() {
        List<Model> result = new ArrayList<>();
        int status = 0;
        result = modelService.queryModelList(status);
        for (Model model : result) {
            System.out.println(model.toString());
        }
    }

    @Test
    public void testModelUploadFile() {

    }
}
