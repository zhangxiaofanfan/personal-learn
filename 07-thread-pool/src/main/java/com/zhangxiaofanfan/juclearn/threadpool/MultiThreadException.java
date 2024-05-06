package com.zhangxiaofanfan.juclearn.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 19:05:04
 */
@Slf4j
public class MultiThreadException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future = executor.submit(() -> {
            int i = 1 / 0;
            return 1;
        });
        log.info("线程执行结束");
    }
}
