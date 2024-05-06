package com.zhangxiaofanfan.juclearn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 学习 ReentrantLock 竞争加锁使用
 *
 * @author zhangxiaofanfan
 * @date 2024-03-11 14:27:48
 */
public class Demo {
    private static final ReentrantLock LOCK = new ReentrantLock();
    public static void main(String[] args) {
        LOCK.lock();
    }
}
