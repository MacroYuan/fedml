package com.myproject.fedml.common;


public enum ModelEnum {
    USER_DEFINED_MODEL(0, "用户自定义模型"),
    LOGISTIC_REGRESSION(1, "lr"),
    RESNET18(2, "resnet18"),
    RESNET56(3, "resnet56"),
    CNN_DROPOUT(4, "cnn");

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
