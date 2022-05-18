package com.myproject.fedml;

import com.myproject.fedml.mbg.model.Dataset;
import com.myproject.fedml.service.DatasetService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author MacroYuan
 * @className DatasetServiceTest
 * @Date 2022/4/15
 * @Version 1.0
 **/
@SpringBootTest
public class DatasetServiceTest {

    @Resource
    private DatasetService datasetService;

//    @Test
//    public void testDatasetInsert() {
//        Dataset dataset = new Dataset();
//        dataset.setDatasetId(11L);
//        dataset.setDatasetName("Test");
//        dataset.setDatasetPath("");
//        int result = datasetService.createDataset(dataset);
//        System.out.println(result);
//    }
}
