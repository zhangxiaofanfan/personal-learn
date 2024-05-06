package com.zhangxiaofanfan.exercise;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用多线程模拟EBT执行过程:
 *  1. java 程序使用多线程的方式调起来多个 EBT 进程(使用python脚本模拟)
 *  2. 每个进程都独立运行;
 *  3. java 获取 EBT 进程执行结果并进行输出;
 *  4. 如果有 EBT 进行执行失败, 则进行异常输出, 并停止所有的 python 进程;
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 10:57:44
 */
@Slf4j
public class MultiThreadEBTDemo {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    private static final Long TIMEOUT = 10L;
    public static void main(String[] args) {
        log.info("执行结果为: {}", ebtDemo("1", "2"));
    }


    /**
     * 模拟 EBT 执行的 demo 场景
     *
     * @param input EBT执行需要的入参
     * @return 运行是否成功
     */
    public static boolean ebtDemo(String ...input) {
        try {
            String frameworkPath = Paths.get(System.getProperty("user.dir"), "python", "framework.py").toString();
            log.info("framework path is {}", frameworkPath);
            String[] cmd = new String[input.length + 2];
            cmd[0] = "python3";
            cmd[1] = frameworkPath;
            System.arraycopy(input, 0, cmd, 2, input.length);
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            boolean result = process.waitFor(TIMEOUT, TimeUnit.SECONDS);
            log.info("脚本执行结果为: {}", result);
            log.info("脚本执行输出code为: {}", process.exitValue());
            log.info("进行执行结果是: {}", output);
            return true;
        } catch (IOException | InterruptedException e) {
            log.warn("异常信息为: {}", e.getMessage());
            return false;
        }
    }
}
