package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 学习 Thread 创建使用
 *
 * @author zhangxiaofanfan
 * @date 2023-12-16 13:49:31
 */
@Slf4j
public class ThreadCreate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("main thread begin...");
        ThreadCreate obj = new ThreadCreate();
        obj.threadCreate03();
        log.info("main thread end...");
    }

    /**
     * 直接使用 Thread 进行创建
     */
    public void threadCreate01() {
        Thread thread = new Thread(() -> log.info("使用 Thread 方法创建的线程执行了"));
        thread.start();
    }

    /**
     * 使用 Runnable 声明任务, 然后使用 Thread 创建线程对象并启动
     */
    public void threadCreate02() {
        Runnable task = () -> log.info("使用 Runnable 声明的任务被线程执行了...");
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * 使用 FutureTask 搭配 Callback 完成 Thread 对象的创建与执行
     */
    public void threadCreate03() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.info("使用 FutureTask 声明的任务被线程执行了...");
            Thread.sleep(2000);
            return 100;
        });
        Thread thread = new Thread(task);
        thread.start();
        log.info("线程返回结果: {}", task.get());
    }
}
