package com.myproject.fedml.controller;

import com.myproject.fedml.common.utils.Result;
import com.myproject.fedml.mbg.model.Model;
import com.myproject.fedml.service.FileService;
import com.myproject.fedml.service.ModelService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author MacroYuan
 * @className ModelController
 * @Date 2022/3/31
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/model")
public class ModelController {
    private static final Logger logger = getLogger(ModelController.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Result create(HttpServletRequest request) {
        try {
            Long modelId = Long.valueOf(request.getParameter("modelId"));
            String modelName = request.getParameter("modelName");
            String modelPath = request.getParameter("modelPath");

            Model model = new Model();
            model.setModelId(modelId);
            model.setModelName(modelName);
            model.setModelPath(modelPath);


            modelService.createModel(model);
        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(HttpServletRequest request, MultipartFile file) {
        try {
            Long modelId = Long.valueOf(request.getParameter("modelId"));
            modelService.uploadModel(modelId, file);
        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result update() {
        try {

        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete() {
        return Result.ok();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result query() {
        return Result.ok();
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public Result queryList() {
        return Result.ok();
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = new byte[1024];

        try {
            Long modelId = Long.valueOf(request.getParameter("modelId"));
            Model model = modelService.queryModel(modelId);
            String modelPath = model.getModelPath();
            // 后面改成Linux系统的
            String fileName = modelPath.substring(modelPath.lastIndexOf("\\")+1);

            response.setHeader("Content-Disposition", "attachment;filename=" +  new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType("application/force-download");

            inputStream = modelService.downloadModel(modelId);
            bufferedInputStream = new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(bytes);
            while (i != -1) {
                outputStream.write(bytes, 0, i);
                i = bufferedInputStream.read(bytes);
            }

        } catch (Exception e) {

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
}