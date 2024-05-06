package com.zhangxiaofanfan.juclearn.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池关闭方法学习
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 12:09:52
 */
@Slf4j
public class StopMethod {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        executor.submit(() -> {
            log.info("method 1 begin");
            TimeUnit.MILLISECONDS.sleep(1000);
            log.info("method 1 end");
            return "1";
        });

        executor.submit(() -> {
            log.info("method 2 begin");
            TimeUnit.MILLISECONDS.sleep(500);
            log.info("method 2 end");
            return "2";
        });

        executor.submit(() -> {
            log.info("method 3 begin");
            TimeUnit.MILLISECONDS.sleep(2000);
            log.info("method 3 end");
            return "3";
        });

        // executor.shutdown();
        List<Runnable> tasks = executor.shutdownNow();
        log.info("阻塞队列中的任务为: {}", tasks);
        log.info("关闭线程池继续向线程池添加任务[begin]");
        executor.submit(() -> {
            log.info("method 4 begin");
            TimeUnit.MILLISECONDS.sleep(2000);
            log.info("method 4 end");
            return "4";
        });
        log.info("关闭线程池继续向线程池添加任务[end]");
    }
}
