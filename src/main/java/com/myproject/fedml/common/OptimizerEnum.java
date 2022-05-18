package com.myproject.fedml.common;

public enum OptimizerEnum {

    SGD(1, "sgd"),
    ADAM(2, "adam");

    private int code;
    private String optimizerName;

    OptimizerEnum(int code, String optimizerName) {
        this.code = code;
        this.optimizerName = optimizerName;
    }

    public int getCode() {
        return code;
    }

    public String getOptimizerName() {
        return optimizerName;
    }

    public static String getOptimizerNameByCode(int code) {
        OptimizerEnum[] optimizerEnums = values();
        for (OptimizerEnum optimizerEnum : optimizerEnums) {
            if (optimizerEnum.getCode() == code) {
                return optimizerEnum.getOptimizerName();
            }
        }
        return null;
    }
}
