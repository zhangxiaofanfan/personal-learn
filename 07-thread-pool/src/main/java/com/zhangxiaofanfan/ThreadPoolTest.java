package com.zhangxiaofanfan;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程学习测试实用类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-11 18:37:44
 */
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("thread-1 线程执行......");
            }
        }, "thread-1").start();
        log.info("main线程执行并结束了");

        FutureTask<String> futureTask = new FutureTask<>(() -> "hello");

        Thread thread = new Thread(futureTask);
        thread.start();
        futureTask.get();
    }
}
