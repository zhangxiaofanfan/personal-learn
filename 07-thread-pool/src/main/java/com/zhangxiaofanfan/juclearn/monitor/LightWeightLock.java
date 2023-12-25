package com.zhangxiaofanfan.juclearn.monitor;

/**
 * 轻量级锁的学习demo
 *
 * @author zhangxiaofanfan
 * @date 2023-12-24 11:00:06
 */
public class LightWeightLock {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        methodA();
    }

    private static void methodA() {
        synchronized (LOCK) {
            methodB();
        }
    }

    private static void methodB() {
        synchronized (LOCK) {
            // 代码块: TODO
        }
    }
}
