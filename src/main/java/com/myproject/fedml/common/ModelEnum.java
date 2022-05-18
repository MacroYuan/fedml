package com.myproject.fedml.common;


public enum ModelEnum {
    USER_DEFINED_MODEL(0, "用户自定义模型"),
    LOGISTIC_REGRESSION(1, "lr"),
    LOGISTIC_REGRESSION_VFL(2, "lr-vfl"),
    VGG11(3, "vgg11"),
    VGG11_BN(4, "vgg11_bn"),
    VGG13(5, "vgg13"),
    VGG13_BN(6, "vgg13_bn"),
    VGG16(7, "vgg16"),
    VGG16_BN(8, "vgg16_bn"),
    VGG19(9, "vgg19"),
    VGG19_BN(10, "vgg19_bn"),
    RESNET56(11, "resnet56"),
    RESNET110(12, "resnet110"),
    CNN_DROPOUT(13, "cnn"),;

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
