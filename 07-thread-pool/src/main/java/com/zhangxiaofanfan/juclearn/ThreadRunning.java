package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟多线程交替执行的场景
 *
 * @author zhangxiaofanfan
 * @date 2023-12-16 14:50:57
 */
@Slf4j
public class ThreadRunning {
    public static void main(String[] args) {
        // 第一个线程启动并执行
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("running...");
            }
        }, "thread-1").start();

        // 第二个线程启动并执行
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("running...");
            }
        }, "thread-2").start();
    }
}
