package com.myproject.fedml.controller;

import com.myproject.fedml.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Result create() {
        return Result.ok();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload() {
        return Result.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result update() {
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
}
