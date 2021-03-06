package com.myproject.fedml.common;

/**
 * @author MacroYuan
 * @className DatasetEnum
 * @Date 2022/3/28
 * @Version 1.0
 **/
public enum DatasetEnum {
    USER_DEFINED_DATASET(0, "用户自定义数据集", ""),
    MNIST(1, "mnist", "$HOME/myProject/FedML/data/mnist"),
    LOAN(2, "loan", ""),
    CIFAR(3, "cifar100", "");

    private int code;
    private String datasetName;
    private String datasetPath;

    DatasetEnum(int code, String datasetName, String datasetPath) {
        this.code = code;
        this.datasetName = datasetName;
        this.datasetPath = datasetPath;
    }

    public static String getDatasetNameByCode(int datasetCode) {
        DatasetEnum[] datasetEnums = values();
        for (DatasetEnum datasetEnum : datasetEnums) {
            if (datasetEnum.getCode() == datasetCode) {
                return datasetEnum.getDatasetName();
            }
        }
        return null;
    }

    public static String getDatasetPathByCode(int datasetCode) {
        DatasetEnum[] datasetEnums = values();
        for (DatasetEnum datasetEnum : datasetEnums) {
            if (datasetEnum.getCode() == datasetCode) {
                return datasetEnum.getDatasetPath();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public String getDatasetPath() {
        return datasetPath;
    }
}
