package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

/**
 * 用来展示 start 和 run 方法区别使用的测试类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 12:41:32
 */
@Slf4j
public class ThreadStartRun {
    public static void main(String[] args) {
        Runnable target = () -> log.info("Runnable 对象执行了");
        Thread newThread = new Thread(target, "thread-1");
        newThread.run();
        newThread.start();
    }
}
