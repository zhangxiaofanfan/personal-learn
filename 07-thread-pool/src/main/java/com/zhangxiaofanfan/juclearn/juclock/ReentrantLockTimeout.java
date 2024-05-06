package com.zhangxiaofanfan.juclearn.juclock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 带有超时效果的加锁机制
 *
 * @author zhangxiaofanfan
 * @date 2024-01-09 23:54:16
 */
@Slf4j
public class ReentrantLockTimeout {
    private static final ReentrantLock LOCK = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                if (!LOCK.tryLock(5, TimeUnit.SECONDS)) {
                    log.info("线程[{}]获得锁失败, 退出执行", Thread.currentThread().getName());
                    return;
                }
            } catch (InterruptedException e) {
                log.info("线程获取锁[LOCK]时被打断操作, 异常信息为: {}", e.getMessage());
                return;
            }
            try {
                log.info("获得锁, 执行后续操作");
            } finally {
                LOCK.unlock();
            }
        }, "t1");
        LOCK.lock();
        t1.start();
    }
}
