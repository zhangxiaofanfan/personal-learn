package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 学习 sleep 和 yield 使用的类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 14:44:05
 */
@Slf4j
public class ThreadSleepYield {
    public static void main(String[] args) throws InterruptedException {
        log.info("before sleep...");
        TimeUnit.SECONDS.sleep(1);
        log.info("after sleep...");
    }
}
