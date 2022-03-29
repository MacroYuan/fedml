CREATE TABLE task (
    id varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务id',
    model_id,
    dataset_id,
    begin_time,
    end_time,
    status,
    describe,
    note,
);

CREATE TABLE dataset (
    id varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据集id',
    dataset_name  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集名称',
    dataset_desc  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集介绍',
    dataset_path,
    dataset_owner varchar(32)   CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人用户ID',
    create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    notes1 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注1',
);

CREATE TABLE model (
    id,
    model_name,
    describe,
    model_path,
    create_time,
    update_time,
    statue,
    note,
);