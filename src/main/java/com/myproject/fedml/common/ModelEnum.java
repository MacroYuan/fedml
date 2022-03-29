package com.myproject.fedml.common;


public enum ModelEnum {
    USER_DEFINED_MODEL(0, "用户自定义模型"),
    LOGISTIC_REGRESSION(1, "逻辑回归模型"),
    RESNET18(2, "Resnet18模型（GAN）"),
    RESNET56(3, "Resnet56模型"),
    CNN_DROPOUT(4, "CNN模型");

    private int code;
    private String modelName;

    ModelEnum(int code, String modelName) {
        this.code = code;
        this.modelName = modelName;
    }

    public int getCode() {
        return code;
    }

    public String getModelName() {
        return modelName;
    }

    public static String getModelNameByCode(int code) {
        ModelEnum[] modelEnums = values();
        for (ModelEnum modelEnum : modelEnums) {
            if (modelEnum.getCode() == code) {
                return modelEnum.getModelName();
            }
        }
        return null;
    }
}
