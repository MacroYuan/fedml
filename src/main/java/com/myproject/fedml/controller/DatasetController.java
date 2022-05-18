package com.myproject.fedml.controller;

import com.myproject.fedml.common.utils.Result;
import com.myproject.fedml.mbg.model.Dataset;
import com.myproject.fedml.mbg.model.Model;
import com.myproject.fedml.service.DatasetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @author MacroYuan
 * @className DatasetController
 * @Date 2022/3/31
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/dataset")
public class DatasetController {
    private static final Logger logger = LoggerFactory.getLogger(DatasetController.class);

    @Resource
    private DatasetService datasetService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Result create(HttpServletRequest request, @RequestBody Dataset dataset) {
        try {
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            String datasetName = request.getParameter("datasetName");
            String datasetPath = request.getParameter("datasetPath");

            Dataset newDataset = new Dataset();
            newDataset.setDatasetId(datasetId);
            newDataset.setDatasetName(datasetName);
            newDataset.setDatasetPath(datasetPath);

            datasetService.createDataset(newDataset);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(HttpServletRequest request, MultipartFile file) {
        try {
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            datasetService.uploadDataset(datasetId, file);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result update(HttpServletRequest request, @RequestBody Dataset dataset) {
        try {
//            Model model = new Model();
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            datasetService.updateDataset(datasetId, dataset);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(HttpServletRequest request) {
        try {
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            datasetService.deleteDataset(datasetId);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result query(HttpServletRequest request) {
        try {
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            datasetService.queryDataset(datasetId);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public Result queryList(HttpServletRequest request) {
        try {
            int status = 0;
            status = Integer.parseInt(request.getParameter("status"));
            datasetService.queryDatasetList(status);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = new byte[1024];

        try {
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            Dataset dataset = datasetService.queryDataset(datasetId);
            String datasetPath = dataset.getDatasetPath();
            // 后面改成Linux系统的
            String fileName = datasetPath.substring(datasetPath.lastIndexOf("/")+1);

            response.setHeader("Content-Disposition", "attachment;filename=" +  new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType("application/force-download");

            inputStream = datasetService.downloadDataset(datasetId);
            bufferedInputStream = new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(bytes);
            while (i != -1) {
                outputStream.write(bytes, 0, i);
                i = bufferedInputStream.read(bytes);
            }

        } catch (Exception e) {
            return;
        } finally {
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
                if (bufferedInputStream!=null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @RequestMapping(value = "/getMetaDataset", method = RequestMethod.GET)
    public Result getMetaDataset(HttpServletRequest request) {
        Map<String, String> data = new HashMap<>();
        try {
            Long datasetId = Long.valueOf(request.getParameter("datasetId"));
            data = datasetService.getMetaDataset(datasetId);
        } catch (Exception e) {
            return Result.error().put("msg", e.getMessage());
        }
        return Result.ok().put("data", data);
    }
}
