package com.zhangxiaofanfan.juclearn.monitor;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 偏向锁实现demo案例
 *
 * @author zhangxiaofanfan
 * @date 2023-12-25 23:21:15
 */
@Slf4j
public class DeviationLock {
    public static void main(String[] args) throws InterruptedException {
        log.info("instance info is {}", ClassLayout.parseInstance(new User()).toPrintable());
        TimeUnit.SECONDS.sleep(5);
        log.info("instance info is {}", ClassLayout.parseInstance(new User()).toPrintable());
    }
    static final Object obj = new Object();
    public static void method1() {
        synchronized (obj) {
            method2();
        }
    }

    public static void method2() {
        synchronized (obj) {
            method3();
        }
    }

    public static void method3() {
        synchronized (obj) {
            // 同步代码块
        }
    }
}

class User {

}
