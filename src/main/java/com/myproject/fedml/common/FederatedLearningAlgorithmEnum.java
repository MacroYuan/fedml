package com.myproject.fedml.common;

public enum FederatedLearningAlgorithmEnum {

    FEDAVG(1, "fedavg"),
    CLASSICAL_VERTICAL_FL(2, "vfl"),
    FEDNOVA(3, "fednova"),
    FEDOPT(4, "fedopt");

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

    public static String getFederatedNameByCode(int code) {
        FederatedLearningAlgorithmEnum[] federatedLearningAlgorithmEnums = values();
        for (FederatedLearningAlgorithmEnum federatedLearningAlgorithmEnum : federatedLearningAlgorithmEnums) {
            if (federatedLearningAlgorithmEnum.getCode() == code) {
                return federatedLearningAlgorithmEnum.getFederatedLearningAlgorithmName();
            }
        }
        return null;
    }
}
