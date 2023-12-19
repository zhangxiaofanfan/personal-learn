package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用 synchronized 关键字解决多线程修改临界资源问题
 *
 * @author zhangxiaofanfan
 * @date 2023-12-19 12:50:32
 */
@Slf4j
public class ThreadShareSynchronized {
    private static int count = 0;
    private static final Object LOCK = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable taskInc = () -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (LOCK) {
                    count++;
                }
            }
        };
        Runnable taskDec = () -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (LOCK) {
                    count--;
                }
            }
        };
        Thread incThread = new Thread(taskInc, "increment-thread");
        Thread decThread = new Thread(taskDec, "decrease-thread");
        incThread.start();
        decThread.start();
        incThread.join();
        decThread.join();
        log.info("count is {}", count);
    }
}
