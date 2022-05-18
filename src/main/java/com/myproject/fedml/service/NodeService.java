package com.myproject.fedml.service;

import java.io.File;
import java.util.Map;

public interface NodeService {
    // TODO：是不是写成工具函数好点
    /**
     * 给定ip：训练器个数的键值对生成相应hostfile
     * @param ip
     * @return
     */
    File generateMpiHostFile(Map<String, String> ip);

    /**
     * 给定ip：[单显卡训练器数量， 。。。]生成相应的map
     * @param gpu
     * @return
     */
    File generateGpuMapping(Map<String, String> gpu);
}
