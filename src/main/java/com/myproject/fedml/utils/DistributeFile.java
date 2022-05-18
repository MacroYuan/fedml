package com.myproject.fedml.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MacroYuan
 * @className DistributeFile
 * @Date 2022/4/22
 * @Version 1.0
 **/
public class DistributeFile {
    public static File generateMpiHostFile(String mpiHostFilePath, Map<String, String> ip) throws IOException {
        /**
         * 10.112.216.104:1
         * 10.112.101.222:1
         */
        String folder = mpiHostFilePath;

        String mpiHostFileName = "mpi_host_file";


        File file = new File(folder + File.separator + mpiHostFileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            fileWriter = new FileWriter(folder + File.separator + mpiHostFileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            // 获取本地ip先写入文件
            InetAddress address = InetAddress.getLocalHost();
            String hostName = address.getHostName();
            String host = address.getHostAddress();
            bufferedWriter.write(host + ":1");


            for (Map.Entry<String, String> entry : ip.entrySet()) {

                String ipAddr =  entry.getKey();
                String processNum = entry.getValue();

                // 本地host不写入文件
                if (ipAddr.equals(host))
                    continue;
                bufferedWriter.write("\n" + ipAddr + ":" + processNum);
            }
        } finally {

            bufferedWriter.close();
        }

        return file;
    }

    public static File generateGrpcIpConfigFile(String grpcIpConfigFilePath, Map<String, String> ip) throws IOException {
        String folder = grpcIpConfigFilePath;
        String GPUMappingFileName = "grpc_ipconfig.csv";

        File file = new File(folder + File.separator + GPUMappingFileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;



        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            fileWriter = new FileWriter(folder + File.separator + GPUMappingFileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            // 先写入标题
            bufferedWriter.write("receiver_id,ip");
            for (Map.Entry<String, String> entry : ip.entrySet()) {

                String id =  entry.getKey();
                String ipAddr = entry.getValue();

                bufferedWriter.write("\n    " + id + "," + ipAddr);
            }
        } finally {
            bufferedWriter.close();
        }

        return file;
    }

    public static File generateGpuMappingFile(String gpuMappingFilePath, Map<String, String> gpu) throws IOException {
        /**
         * # this is used for 2 clients and 1 server training within a single machine which has 4 GPUs
         * mapping_default:
         *     10.112.216.104: [1]
         *     10.112.101.222: [1]
         */
        String folder = gpuMappingFilePath;
        String GPUMappingFileName = "gpu_mapping.yaml";

        File file = new File(folder + File.separator + GPUMappingFileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;



        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            fileWriter = new FileWriter(folder + File.separator + GPUMappingFileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("mapping_default:");
            for (Map.Entry<String, String> entry : gpu.entrySet()) {

                String ipAddr =  entry.getKey();
                String mapping = entry.getValue();

                bufferedWriter.write("\n    " + ipAddr + ": " + mapping);
            }
        } finally {
            bufferedWriter.close();
        }

        return file;
    }

    private static String buildFolder(String path) throws IOException {
        File file = new File(path);

        // 判断是否存在
        if (!file.exists() && file.isDirectory()) {
            System.out.println("文件夹不存在，创建文件夹");
            file.mkdir();
        } else {
            // 路径存在，不操作
        }
        return path;
    }

    // 测试函数
    // 测完后记得删除
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("10.3.8.211", "1");
        map.put("10.3.8.216", "1");
        try {
            File file = generateMpiHostFile("D:/test", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
