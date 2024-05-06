package com.zhangxiaofanfan.juclearn.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于 AQS 实现的不可重入锁
 *
 * @author zhangxiaofanfan
 * @date 2024-02-01 09:08:57
 */
@Slf4j
public class MyNonReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            lock.lock();
            try {
                log.info("locking......");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                log.info("unlocking......");
                lock.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(2);
        lock.unlock();
        //
        // new Thread(() -> {
        //     lock.lock();
        //     try {
        //         log.info("locking......");
        //         try {
        //             TimeUnit.SECONDS.sleep(1);
        //         } catch (InterruptedException e) {
        //             throw new RuntimeException(e);
        //         }
        //     } finally {
        //         log.info("unlocking......");
        //         lock.unlock();
        //     }
        // }, "t2").start();


    }
}

/**
 * 不可重入锁
 */
class MyLock implements Lock {

    private MySync mySync = new MySync();

    /**
     * 独占锁, 不可重入锁, 当前线程也不可以重新加锁
     */
    class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                // 表示加锁成功, 设置所属线程为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread() == Thread.currentThread()) {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
            return false;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 创建一个新的条件变量
         *
         * @return 新的条件变量
         */
        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    /**
     * 加锁
     */
    @Override
    public void lock() {
        mySync.acquire(1);
    }

    /**
     * 加可被打断打断的锁
     *
     * @throws InterruptedException 打断出现的异常
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    /**
     * 尝试加锁
     *
     * @return 加锁结果
     */
    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    /**
     * 带有超时的加锁
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return 是否加锁成功
     * @throws InterruptedException 打断异常
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        mySync.release(1);
    }

    /**
     * 创建条件变量
     *
     * @return 条件变量对象
     */
    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }
}
