package com.zhangxiaofanfan.mythreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-09 14:41:57
 */
@Slf4j
public class MyThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {
            long nanos = TimeUnit.SECONDS.toNanos(20);
            long startTime = System.nanoTime();
            lock.lock();
            try {
                try {
                    nanos = condition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    nanos = nanos - (System.nanoTime() - startTime);
                    log.info("线程[{}]被打断, 等待时间为: {}, 打断标志位: {}", Thread.currentThread(), nanos, Thread.interrupted());
                }
            } finally {
                lock.unlock();
            }
        }, "test-thread");
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }
}
