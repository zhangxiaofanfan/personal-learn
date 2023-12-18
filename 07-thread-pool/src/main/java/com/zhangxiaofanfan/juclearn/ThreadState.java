package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 学习 Java 语言层面线程状态使用
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 23:43:50
 */
@Slf4j
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            synchronized (ThreadState.class) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("{} 线程休眠被中断, 中断信息为: {}", Thread.currentThread().getName(), e.getMessage());
                }
            }
        };

        Thread t1 = new Thread(() -> log.info("running"), "t1");

        Thread t2 = new Thread(() -> {
            for (;;);
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> log.info("running"), "t3");
        t3.start();

        Thread t4 = new Thread(task, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                log.error("{} 线程休眠被中断, 中断信息为: {}", Thread.currentThread().getName(), e.getMessage());
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(task, "t6");
        t6.start();

        TimeUnit.MILLISECONDS.sleep(500);

        // 打印线程状态
        log.info("t1 state is {}", t1.getState());
        log.info("t2 state is {}", t2.getState());
        log.info("t3 state is {}", t3.getState());
        log.info("t4 state is {}", t4.getState());
        log.info("t5 state is {}", t5.getState());
        log.info("t6 state is {}", t6.getState());
    }
}
