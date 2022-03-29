package com.myproject.fedml.common;

public enum FederatedLearningAlgorithmEnum {

    USER_DEFINED_ALGORITHM(0, "用户自定义联邦算法"),
    FEDAVG(1, "fedavg"),
    FEDNOVA(2, "fednova"),
    FEDOPT(3, "fedopt"),
    CLASSICAL_VERTICAL_FL(4, "classical vertical federated learning");

    private int code;
    private String federatedLearningAlgorithmName;

    FederatedLearningAlgorithmEnum(int code, String federatedLearningAlgorithmName) {
        this.code = code;
        this.federatedLearningAlgorithmName = federatedLearningAlgorithmName;
    }

    public int getCode() {
        return code;
    }

    public String getFederatedLearningAlgorithmName() {
        return federatedLearningAlgorithmName;
    }
}
