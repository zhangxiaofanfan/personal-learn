package com.zhangxiaofanfan.juclearn.designpatterns;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 保护性暂停: 多线程通信
 *
 * @author zhangxiaofanfan
 * @date 2023-12-28 12:33:46
 */
@Slf4j
public class ThreadGuardedSuspension {
    public static void main(String[] args) {
        Guarded<List<String>> guarded = new Guarded<>();
        new Thread(() -> {
            List<String> response = guarded.get(2000);
            log.info("response is {}", response);
        }, "t1").start();

        new Thread(() -> guarded.finish(producerResponse()), "t2").start();
    }

    private static List<String> producerResponse() {
        Random random = new Random();
        int duringTime = random.nextInt(3) + 1;
        log.info("生产结果耗时: {}m", duringTime);
        try {
            TimeUnit.SECONDS.sleep(duringTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList("结果1", "结果2", "结果3", "结果4");
    }
}

/**
 * 封装了共享变量的线程安全类
 */
@Slf4j
class Guarded<T> {
    private T response;

    /**
     * 带有超时功能、线程安全的结果获取函数
     *
     * @param timeout 超时时限, 单位为毫秒
     * @return  返回获取结果
     */
    public T get(long timeout) {
        synchronized (this) {
            long beginTime = System.currentTimeMillis();
            long timeRemaining;
            // 防止虚假唤醒
            while (response == null) {
                timeRemaining = timeout - (System.currentTimeMillis() - beginTime);
                if (timeRemaining <= 0) {
                    log.warn("等待超时, 超时时间为: {}ms", timeout);
                    break;
                }
                try {
                    this.wait(timeRemaining);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("等待时间为: {}ms", System.currentTimeMillis() - beginTime);
            return response;
        }
    }

    /**
     * 完成之后将共享结果防止到 response 中
     *
     * @param response 线程操作完成之后所需要进行共享的结果
     */
    public synchronized void finish(T response) {
        this.response = response;
        this.notifyAll();
    }
}
