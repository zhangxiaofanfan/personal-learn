package com.zhangxiaofanfan.juclearn.wait;

import lombok.extern.slf4j.Slf4j;

/**
 * 对比 wait 和 sleep 方法区别使用的类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-26 23:46:02
 */
@Slf4j
public class WaitSleep {
    private static final Object LOCK = new Object();
    public static void main(String[] args) throws InterruptedException {
        test01();
//        test02();
    }

    private static void test01 () throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                log.info("{} 加锁执行代码块", Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("{} 释放锁之后的操作...", Thread.currentThread().getName());
            }
        }, "t1").start();

        Thread.sleep(1000);
        synchronized (LOCK) {
            log.info("{} 加锁执行代码块", Thread.currentThread().getName());
        }
        log.info("{} 释放锁之后的操作...", Thread.currentThread().getName());
    }

    private static void test02 () throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                log.info("{} 加锁执行代码块", Thread.currentThread().getName());
                try {
                    LOCK.wait(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("{} 释放锁之后的操作...", Thread.currentThread().getName());
            }
        }, "t1").start();

        Thread.sleep(1000);
        synchronized (LOCK) {
            log.info("{} 加锁执行代码块", Thread.currentThread().getName());
        }
        log.info("{} 释放锁之后的操作...", Thread.currentThread().getName());
    }
}
