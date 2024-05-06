package com.zhangxiaofanfan.juclearn.designpatterns;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步模式: 固定运行顺序
 *
 * @author zhangxiaofanfan
 * @date 2024-01-15 18:08:56
 */
@Slf4j
public class SequentialExecution {
    private static final Object LOCK = new Object();
    private static final ReentrantLock ROOM = new ReentrantLock();
    private static final Condition WAIT_LOG_A = ROOM.newCondition();
    private static final Condition WAIT_LOG_B = ROOM.newCondition();
    private static final Condition WAIT_LOG_C = ROOM.newCondition();
    private static volatile boolean CAN_LOG_A = true;
    private static volatile boolean CAN_LOG_B = false;
    private static volatile boolean CAN_LOG_C = false;
    private static final Integer THREAD_NUM = 5;

    public static void main(String[] args) {
        // sequentialParkUnpark();
        // sequentialWaitNotify();
        sequentialAwaitSignal();
    }

    /**
     * 使用 park 和 unpark 实现先打印输出2, 在打印输出1
     */
    public static void sequentialParkUnpark() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.info("日志打印: 1");
        }, "t1");
        t1.start();
        new Thread(() -> {
            log.info("日志打印: 2");
            LockSupport.unpark(t1);
        }, "t2").start();
    }

    /**
     * 使用 Wait-Notify 机制实现 abc 顺序打印
     *  5个线程输出a;
     *  5个线程输出b;
     *  5个线程输出c;
     */
    public static void sequentialWaitNotify() {
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                synchronized (LOCK) {
                    while (!CAN_LOG_A) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            log.info("线程等待过程中被打断, 异常信息为: {}", e.getMessage());
                        }
                    }
                    log.info("输出: A");
                    CAN_LOG_A = false;
                    CAN_LOG_B = true;
                    CAN_LOG_C = false;
                    LOCK.notifyAll();
                }
            }, "log-A-" + i).start();

            new Thread(() -> {
                synchronized (LOCK) {
                    while (!CAN_LOG_B) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            log.info("线程等待过程中被打断, 异常信息为: {}", e.getMessage());
                        }
                    }
                    log.info("输出: B");
                    CAN_LOG_A = false;
                    CAN_LOG_B = false;
                    CAN_LOG_C = true;
                    LOCK.notifyAll();
                }
            }, "log-B-" + i).start();

            new Thread(() -> {
                synchronized (LOCK) {
                    while (!CAN_LOG_C) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            log.info("线程等待过程中被打断, 异常信息为: {}", e.getMessage());
                        }
                    }
                    log.info("输出: C");
                    CAN_LOG_A = true;
                    CAN_LOG_B = false;
                    CAN_LOG_C = false;
                    LOCK.notifyAll();
                }
            }, "log-C-" + i).start();
        }
    }

    /**
     * 使用 Await-Signal 机制实现 abc 顺序打印
     *  5个线程输出a;
     *  5个线程输出b;
     *  5个线程输出c;
     */
    public static void sequentialAwaitSignal() {
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                ROOM.lock();
                try {
                    while (!CAN_LOG_A) {
                        try {
                            WAIT_LOG_A.await();
                        } catch (InterruptedException e) {
                            log.warn("线程被打断, 异常原因为: {}", e.getMessage());
                        }
                    }
                    log.info("输出: A");
                    CAN_LOG_A = false;
                    CAN_LOG_B = true;
                    CAN_LOG_C = false;
                    WAIT_LOG_B.signalAll();
                } finally {
                    ROOM.unlock();
                }
            }, "log-A-" + i).start();
            new Thread(() -> {
                ROOM.lock();
                try {
                    while (!CAN_LOG_B) {
                        try {
                            WAIT_LOG_B.await();
                        } catch (InterruptedException e) {
                            log.warn("线程被打断, 异常原因为: {}", e.getMessage());
                        }
                    }
                    log.info("输出: B");
                    CAN_LOG_A = false;
                    CAN_LOG_B = false;
                    CAN_LOG_C = true;
                    WAIT_LOG_C.signal();
                } finally {
                    ROOM.unlock();
                }
            }, "log-B-" + i).start();
            new Thread(() -> {
                ROOM.lock();
                try {
                    while (!CAN_LOG_C) {
                        try {
                            WAIT_LOG_C.await();
                        } catch (InterruptedException e) {
                            log.warn("线程被打断, 异常原因为: {}", e.getMessage());
                        }
                    }
                    log.info("输出: C");
                    CAN_LOG_A = true;
                    CAN_LOG_B = false;
                    CAN_LOG_C = false;
                    WAIT_LOG_A.signalAll();
                } finally {
                    ROOM.unlock();
                }
            }, "log-C-" + i).start();
        }
    }
}
