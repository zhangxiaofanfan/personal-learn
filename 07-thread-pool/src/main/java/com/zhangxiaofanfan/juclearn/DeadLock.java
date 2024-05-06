package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示死锁的生成与排查
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 08:56:07
 */
@Slf4j
public class DeadLock {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (LOCK_A) {
                sleepMethod(500);
                synchronized (LOCK_B) {
                    log.info("线程[{}]获得了两把锁[LOCK_A和LOCK_B], 执行业务代码", Thread.currentThread().getName());
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (LOCK_B) {
                sleepMethod(1000);
                synchronized (LOCK_A) {
                    log.info("线程[{}]获得了两把锁[LOCK_A和LOCK_B], 执行业务代码", Thread.currentThread().getName());
                }
            }
        }, "t2").start();
    }

    private static void sleepMethod(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            log.info("休眠被打断, 原因: {}", e.getMessage());
        }
    }
}
