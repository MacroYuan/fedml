package com.myproject.fedml.common;

public enum BackendEnum {
    SINGLE_PROCESS(1, "single_process"),
    GRPC(2, "GPRC"),
    MPI(3, "MPI");

    private int code;
    private String backendName;

    BackendEnum(int code, String backendName) {
        this.code = code;
        this.backendName = backendName;
    }

    public int getCode() {
        return code;
    }

    public String getBackendName() {
        return backendName;
    }

    public static String getBackendNameByCode(int code) {
        BackendEnum[] backendEnums = values();
        for (BackendEnum backendEnum : backendEnums) {
            if (backendEnum.getCode() == code) {
                return backendEnum.getBackendName();
            }
        }
        return null;
    }
}
