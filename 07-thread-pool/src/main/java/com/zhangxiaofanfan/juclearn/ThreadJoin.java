package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 学习 join 方法使用的类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 15:30:11
 */
@Slf4j
public class ThreadJoin {
    static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("thread-1 开始执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("thread-1 结束执行");
            x = 10;
        }, "thread-1");
        t1.start();
        t1.join();
        log.info("main 开始执行, {}", x);
        log.info("main 结束执行");
    }
}
