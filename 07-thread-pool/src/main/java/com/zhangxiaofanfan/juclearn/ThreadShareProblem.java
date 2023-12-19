package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

/**
 * 探索多线程下线程共享引起的问题
 *
 * @author zhangxiaofanfan
 * @date 2023-12-19 10:30:14
 */
@Slf4j
public class ThreadShareProblem {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable taskInc = () -> {
            for (int i = 0; i < 5000; i++) {
                count++;
            }
        };
        Runnable taskDec = () -> {
            for (int i = 0; i < 5000; i++) {
                count--;
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
