package com.zhangxiaofanfan.juclearn.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量的学习和使用, 模拟场景:
 *  1. 小帆使用CPU时需要喝完奶茶才能进行后续操作
 *  2. 小棉使用CPU时需要喝完咖啡才能进行后续操作
 *  3. 外卖员在主线程开启1秒后送来奶茶, 再过1秒之后送来咖啡
 *
 * @author zhangxiaofanfan
 * @date 2024-01-15 12:18:16
 */
@Slf4j
public class ReentrantLockCondition {
    private static final ReentrantLock ROOM = new ReentrantLock();
    private static final Condition MILKY_TEA_WAIT_SET = ROOM.newCondition();
    private static final Condition COFFEE_WAIT_SET = ROOM.newCondition();
    private static volatile Boolean hasMilkyTea = false;
    private static volatile Boolean hasCoffee = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            ROOM.lock();
            try {
                log.info("开始前置工作");
                while (!hasMilkyTea) {
                    try {
                        MILKY_TEA_WAIT_SET.await();
                    } catch (InterruptedException e) {
                        log.error("等待过程被打断, 异常信息是: {}", e.getMessage());
                    }
                }
                log.info("喝完奶茶, 可以开始后续工作");
            } finally {
                ROOM.unlock();
            }
        }, "小帆").start();
        new Thread(() -> {
            ROOM.lock();
            try {
                log.info("开始前置工作");
                while (!hasCoffee) {
                    try {
                        COFFEE_WAIT_SET.await();
                    } catch (InterruptedException e) {
                        log.error("等待过程被打断, 异常信息是: {}", e.getMessage());
                    }
                }
                log.info("喝完咖啡, 可以开始后续工作");
            } finally {
                ROOM.unlock();
            }
        }, "小棉").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            ROOM.lock();
            try {
                log.info("奶茶已经送到");
                hasMilkyTea = true;
                MILKY_TEA_WAIT_SET.signalAll();
            } finally {
                ROOM.unlock();
            }
        }, "外卖员-奶茶").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            ROOM.lock();
            try {
                log.info("咖啡已经送到");
                hasCoffee = true;
                COFFEE_WAIT_SET.signalAll();
            } finally {
                ROOM.unlock();
            }
        }, "外卖员-咖啡").start();
    }
}
