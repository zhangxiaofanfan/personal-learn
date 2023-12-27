package com.zhangxiaofanfan.juclearn.wait;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 正确使用 wait notify 类
 * 案例背景:
 *  1. 线程[xiaomian]在运行过程中需要等待条件成立(星巴克), 才能继续向下执行;
 *  2. 线程[xiaofan]在运行过程中需要等待条件成立(蜜雪冰城), 才能继续向下执行;
 *  3. 1秒之后线程[market-1]送来星巴克;
 *  4. 2秒之后线程[market-2]送来蜜雪冰城;
 *
 * @author zhangxiaofanfan
 * @date 2023-12-27 23:24:43
 */
@Slf4j
public class WaitNotify {
    private static final Object LOCK = new Object();
    private static Boolean hasXingBaBa = false;
    private static Boolean hasMiXue = false;
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (LOCK) {
                log.info("前置工作...");
                while (!hasMiXue) {
                    try {
                        log.info("等待蜜雪冰城...");
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("蜜雪冰城喝完, 执行后置工作...");
            }
        }, "xiaofan").start();

        new Thread(() -> {
            synchronized (LOCK) {
                log.info("前置工作...");
                while (!hasXingBaBa) {
                    try {
                        log.info("等待星巴克...");
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("星巴克喝完, 执行后置工作...");
            }
        }, "xiaomian").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (LOCK) {
                log.info("星巴克送到了");
                hasXingBaBa = true;
                LOCK.notifyAll();
            }
        }, "market-1").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (LOCK) {
                log.info("蜜雪冰城送到了");
                hasMiXue = true;
                LOCK.notifyAll();
            }
        }, "market-2").start();
    }
}
