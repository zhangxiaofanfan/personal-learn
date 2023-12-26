package com.zhangxiaofanfan.juclearn.monitor;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import java.util.concurrent.TimeUnit;

/**
 * 案例演示锁升级过程
 *
 * @author zhangxiaofanfan
 * @date 2023-12-26 12:16:40
 */
@Slf4j
public class LockEscalation {
    static final Object LOCK = new Object();
    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
    }

    /**
     * 偏向锁: 只有当前线程在使用锁
     */
    public static void test01() {
        new Thread(() -> {
            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
            }

            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
            }

            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
            }
        }).start();
    }

    /**
     * 轻量级锁: 两个线程交替使用
     */
    public static void test02() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
            }
        }, "t2");
        t1.start();
        t2.start();
    }

    /**
     * Monitor锁: 两个线程交替使用
     */
    public static void test03() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                t1.join();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (LOCK) {
                log.info(ClassLayout.parseInstance(LOCK).toPrintable());
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
