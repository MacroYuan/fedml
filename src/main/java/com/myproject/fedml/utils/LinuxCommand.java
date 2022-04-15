package com.myproject.fedml.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * LinuxCommands通过调起线程执行command命令，再将控制台输出作为输入结果返回
 * @author fengyu
 * @create 2021/8/11 20:57
 */
public class LinuxCommand {
    //对于不含“|>”的命令，直接以String类型为参数即可
    public static String run(String command) throws IOException {
        Scanner input = null;
        String result = "";
        Process process = null;
        try{
            //执行
            System.out.println(command);
            process = Runtime.getRuntime().exec(command);
            try{
                //等待执行完成
                process.waitFor(10, TimeUnit.SECONDS);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            InputStream is = process.getInputStream();
            input = new Scanner(is);
            while (input.hasNextLine()){
                result += input.nextLine() + "\n";
            }
            result = command + "\n" + result;
        }finally {
            if (input != null){
                input.close();
            }
            if (process != null){
                process.destroy();
            }
        }

        return result;
    }

    public static String run(String[] command) throws IOException {
        Scanner input = null;
        String result = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            try {
                //等待命令执行完成
                process.waitFor(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            InputStream is = process.getInputStream();
            input = new Scanner(is);
            while (input.hasNextLine()) {
                result += input.nextLine() + "\n";
            }
            result = command + "\n" + result; //加上命令本身
        } finally {
            if (input != null) {
                input.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }
}
