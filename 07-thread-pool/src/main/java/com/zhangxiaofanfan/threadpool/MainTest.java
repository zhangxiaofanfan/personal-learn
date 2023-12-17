package com.zhangxiaofanfan.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午12:59
 * @description 线程池测试类
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(5, 5, (queue, task) -> {});
        for (int i = 0; i < 10; i++) {
            int j = i;
            pool.execute(() -> {
                log.debug("task begin.....{}", j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("task end.....{}", j);
            });
        }
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
    }


}
