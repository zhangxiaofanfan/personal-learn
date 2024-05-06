package com.zhangxiaofanfan.juclearn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 加锁过程学习
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 10:33:42
 */
public class ReentrantLockAcquire {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t3").start();
    }
}
