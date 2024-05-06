package com.zhangxiaofanfan.mythreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列自定义实现
 *
 * @author zhangxiaofanfan
 * @date 2024-04-09 14:42:30
 */
@Slf4j
public class MyBlockQueue<T> {

    private final Deque<T> deque;
    private final Integer maxSize;
    private final RefusalStrategy<T> reject;
    private final ReentrantLock lock;
    private final Condition emptyCondition;
    private final Condition fullCondition;

    public MyBlockQueue(Integer maxSize, RefusalStrategy<T> reject) {
        this.maxSize = maxSize;
        this.reject = reject;
        this.lock = new ReentrantLock();
        this.deque = new ArrayDeque<>();
        this.emptyCondition = this.lock.newCondition();
        this.fullCondition = this.lock.newCondition();
    }

    /**
     * 获取最新的任务
     *
     * @return 获取最新的任务, 如果阻塞队列中不存在任务, 进入 emptyCondition 等待
     */
    public T offer() {
        lock.lock();
        try {
            while (!this.deque.isEmpty()) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    log.info("等待过程中线程[{}]被打断", Thread.currentThread().getName());
                }
            }
            return getBlockDequeFirstTask();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取最新的任务
     *
     * @return 获取最新的任务, 如果阻塞队列中不存在任务, 进入 emptyCondition 等待
     */
    public T offer(long timeout, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(timeout);
        long startTime = System.nanoTime();
        lock.lock();
        try {
            while (!this.deque.isEmpty()) {
                if (nanos <= 0) {
                    log.info("阻塞队列空, 获取任务超时");
                    return null;
                }
                try {
                    nanos = emptyCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    nanos = nanos - (System.nanoTime() - startTime);
                    log.info("等待过程中线程[{}]被打断, 剩余等待时间为: {}", Thread.currentThread().getName(), nanos);
                }
            }
            return getBlockDequeFirstTask();
        } finally {
            lock.unlock();
        }
    }


    /**
     * 向阻塞队列队尾添加任务
     *
     * @param task 待添加的任务
     */
    public void put(T task) {
        lock.lock();
        try {
            while (this.deque.size() >= this.maxSize) {
                try {
                    log.info("阻塞队列已满, 等待其他线程消费任务");
                    this.fullCondition.await();
                } catch (InterruptedException e) {
                    log.warn("线程[{}]执行过程中被打断", Thread.currentThread().getName());
                }
            }
            putBlockDequeLatestTask(task);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 向阻塞队列队尾添加任务
     *
     * @param task 待添加的任务
     */
    public void put(T task, long timeout, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(timeout);
        long startTime = System.nanoTime();
        lock.lock();
        try {
            while (this.deque.size() >= this.maxSize) {
                if (nanos <= 0) {
                    log.info("阻塞队列满, 保存任务超时");
                    return ;
                }
                try {
                    log.info("阻塞队列已满, 等待其他线程消费任务");
                    nanos = this.fullCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    nanos = nanos - (System.nanoTime() - startTime);
                    log.warn("线程[{}]执行过程中被打断", Thread.currentThread().getName());
                }
            }
            putBlockDequeLatestTask(task);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 向阻塞队列队尾添加任务
     *
     * @param task 待添加的任务
     */
    public void tryPut(T task) {
        lock.lock();
        try {
            if (this.deque.size() >= this.maxSize) {
                this.reject.reject(this, task);
            } else {
                putBlockDequeLatestTask(task);
            }
        } finally {
            lock.unlock();
        }
    }


    /**
     * 获取阻塞队列的对一个任务, 并唤醒 fullCondition 上等待的线程
     *
     * @return 返回阻塞队列第一个任务
     */
    public T getBlockDequeFirstTask() {
        T first = this.deque.removeFirst();
        log.info("获取到最新任务, 最新任务为: {}", first);
        fullCondition.signalAll();
        return first;
    }

    /**
     * 获取阻塞队列的对一个任务, 并唤醒 fullCondition 上等待的线程
     *
     * @param task 待添加的任务
     */
    public void putBlockDequeLatestTask(T task) {
        this.deque.addLast(task);
        log.info("向阻塞队列中添加任务, 新增任务为: {}", task);
        emptyCondition.signalAll();
    }

    public Integer getSize() {
        lock.lock();
        try {
            return this.deque.size();
        } finally {
            lock.unlock();
        }
    }
}