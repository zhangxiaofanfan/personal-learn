package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程学习类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 20:01:58
 */
@Slf4j
public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            while (true) {
                log.info("检测进程垃圾情况...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("模拟垃圾处理过程, 等待 1 秒");
                } catch (InterruptedException e) {
                    log.error("垃圾处理过程中遇到异常, 异常信息: {}", e.getMessage());
                }
                log.info("处理垃圾结束");
            }
        };

        Thread daemon = new Thread(task, "daemon");
        // 设置 daemon 线程为守护线程
        daemon.setDaemon(true);
        daemon.start();

        log.info("主线程开始运行...");
        TimeUnit.SECONDS.sleep(3);
        log.info("主线程结束运行...");
    }
}
