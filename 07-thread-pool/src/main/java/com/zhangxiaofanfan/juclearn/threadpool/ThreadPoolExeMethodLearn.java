package com.zhangxiaofanfan.juclearn.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程池运行方法集合
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 08:58:43
 */
@Slf4j
public class ThreadPoolExeMethodLearn {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // executeMethod();
        // submitMethod();
        // invokeAllMethod();
        // invokeAnyMethod();
    }

    /**
     * 学习 execute 执行使用的方法, 接收Runnable类型的参数, 没有返回值, 执行完正常退出
     */
    public static void executeMethod() {
        executor.execute(() -> log.info("日志输出"));
    }

    public static void submitMethod() throws ExecutionException, InterruptedException {
        // 接收 Runnable 类型任务
        executor.submit(() -> log.info("Runnable 类型任务执行"));
        // 接收 Callable 类型任务
        Future<String> callableSubmit = executor.submit(() -> {
            log.info("Callable begin......");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Callable end......");
            return "submit";
        });
        log.info("submit get before...");
        log.info("callable get is {}", callableSubmit.get());
        log.info("submit get end...");
    }

    public static void invokeAllMethod() throws InterruptedException, ExecutionException {
        long beginTime = System.currentTimeMillis();
        List<Future<String>> futures = executor.invokeAll(Arrays.asList(
                () -> {
                    log.info("task 1 begin...");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    log.info("task 1 end...");
                    return "1";
                },
                () -> {
                    log.info("task 2 begin...");
                    TimeUnit.MILLISECONDS.sleep(500);
                    log.info("task 2 end...");
                    return "2";
                },
                () -> {
                    log.info("task 3 begin...");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    log.info("task 3 end...");
                    return "3";
                }
        ));
        // for (Future<String> future : futures) {
        //     log.info("result is {}", future.get());
        // }
        long endTime = System.currentTimeMillis();
        log.info("任务执行完毕, 耗时: {}", endTime - beginTime);
    }


    public static void invokeAnyMethod() throws InterruptedException, ExecutionException {
        long beginTime = System.currentTimeMillis();
        String result = executor.invokeAny(Arrays.asList(
                () -> {
                    log.info("task 1 begin...");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    log.info("task 1 end...");
                    return "1";
                },
                () -> {
                    log.info("task 2 begin...");
                    TimeUnit.MILLISECONDS.sleep(500);
                    log.info("task 2 end...");
                    return "2";
                },
                () -> {
                    log.info("task 3 begin...");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    log.info("task 3 end...");
                    return "3";
                }
        ));
        long endTime = System.currentTimeMillis();
        log.info("任务执行完毕, 耗时: {}, 结果是: {}", endTime - beginTime, result);
    }

}
