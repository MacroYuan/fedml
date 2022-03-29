package com.myproject.fedml.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MacroYuan
 * @className Result
 * @Date 2022/3/28
 * @Version 1.0
 **/
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", ResultCode.SUCCESS.getCode());
        put("msg", ResultCode.SUCCESS.getMessage());
    }

    public static Result error() {
        return error(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
    }

    public static Result error(String msg) {
        return error(ResultCode.FAILED.getCode(), msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
