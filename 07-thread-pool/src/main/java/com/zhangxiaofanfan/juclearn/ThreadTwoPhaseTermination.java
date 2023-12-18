package com.zhangxiaofanfan.juclearn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 两阶段终止模式实现类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-18 19:13:31
 */
@Slf4j
public class ThreadTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        MonitorClass monitor = new MonitorClass();
        monitor.start();

        // 模拟进程运行5秒之后关系系统
        TimeUnit.SECONDS.sleep(5);
        log.info("应用程序被停止, 停止监控线程.");
        monitor.stop();
    }
}

/**
 * 监控类监控系统系统数据, stop操作关闭监控进程
 */
@Slf4j
class MonitorClass {
    Thread monitor;

    public void start() {
        Runnable monitorTask = () -> {
            while (true) {
                Thread monitorThread = Thread.currentThread();
                if (monitorThread.isInterrupted()) {
                    log.info("线程被打断, 关闭资源流");
                    break;
                }
                log.info("模拟监控操作收集数据");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("当前线程被打断");
                    // 休眠被打断中断标志位为false, 需要在运行状态调用 Interrupt 方法
                    monitorThread.interrupt();
                }
            }
            log.info("监控任务结束");
        };
        monitor = new Thread(monitorTask, "monitor");
        monitor.start();
    }

    /**
     * stop 方法通过中断的方式对监控类实行优雅的打断操作
     */
    public void stop() {
        monitor.interrupt();
    }
}
