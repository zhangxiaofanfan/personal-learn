package com.zhangxiaofanfan.juclearn.juclock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可冲入特性演示
 *
 * @author zhangxiaofanfan
 * @date 2024-01-09 23:42:04
 */
@Slf4j
public class ReentrantLockReentry {
    private static final ReentrantLock LOCK = new ReentrantLock();
    public static void main(String[] args) {
        LOCK.lock();
        try {
            log.info("main running......");
            m1();
        } finally {
            LOCK.unlock();
        }
    }

    public static void m1() {
        LOCK.lock();
        try {
            log.info("m1 running......");
            m2();
        } finally {
            LOCK.unlock();
        }
    }
    public static void m2() {
        LOCK.lock();
        try {
            log.info("m2 running......");
        } finally {
            LOCK.unlock();
        }
    }
}
