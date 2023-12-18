package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 学习 interrupt 方法使用的类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 16:17:02
 */
@Slf4j
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
//        interruptBlock();
        interruptNonBlock();
    }

    /**
     * 使用 Interrupt 方法打断 阻塞 中的线程
     *
     * @throws InterruptedException 中断异常
     */
    public static void interruptBlock() throws InterruptedException {
        Thread blockThread = new Thread(() -> {
            log.info("开始休眠: 10s...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                log.info("休眠时间被打断..., 异常信息为: {}", e.getMessage());
            }
            log.info("结束休眠: 10s...");
        }, "block-thread");
        blockThread.start();
        TimeUnit.SECONDS.sleep(1);
        blockThread.interrupt();
        log.info("执行打断完成, 主线程执行结束...");
    }

    /**
     * 使用 Interrupt 方法打断 运行 中的线程
     * @throws InterruptedException 中断异常
     */
    public static void interruptNonBlock() throws InterruptedException {
        Thread nonBlockThread = new Thread(() -> {
            boolean hasPrint = false;
            while (true) {
                Thread curThread = Thread.currentThread();
                if (curThread.isInterrupted()) {
                    log.info("当前线程被打断执行, 可以退出了");
                    break;
                }
//                if (curThread.isInterrupted()) {
//                    if (!hasPrint) {
//                        hasPrint = true;
//                        log.info("当前线程被打断执行, 无需退出...");
//                    }
//                }
            }
        }, "block-thread");
        nonBlockThread.start();
        TimeUnit.SECONDS.sleep(1);
        nonBlockThread.interrupt();
        log.info("执行打断完成, 主线程执行结束...");
    }
}
