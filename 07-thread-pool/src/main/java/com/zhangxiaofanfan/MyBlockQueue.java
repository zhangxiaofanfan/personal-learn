package com.zhangxiaofanfan;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午12:59
 * @description 自定义阻塞队列实现
 *      1. ReentrantLock定义的锁是自定义锁，与 synchronized 关键字不同：
 *          ReentrantLock 锁定资源的后续操作必须在try语句块中执行，以保证尽管出现异常，锁也可以正常释放掉；
 *          lock.lock();try { 执行代码... } finally { lock.unlock(); }
 */
public class MyBlockQueue<T> {
    /**
     * maxSize: 自定义阻塞队列存放任务的最大数量
     * deque: 存放任务的队列
     * lock: 锁，保护临界资源的安全
     * EMPTY_WAIT_SET: 当阻塞队列已空，并且有线程消费任务时，将线程存放在 EMPTY_WAIT_SET 中进行等待
     * FULL_WAIT_SET: 当阻塞队列已满，并且有线程提供任务时，将线程存放在 FULL_WAIT_SET 中进行等待
     * LOG: 记录日志使用
     */
    private Integer maxSize;
    private Deque<T> deque = new ArrayDeque<T>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition EMPTY_WAIT_SET = lock.newCondition();
    private Condition FULL_WAIT_SET = lock.newCondition();
    private static Log LOG = LogFactory.get();

    public MyBlockQueue(Integer maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 向阻塞队列尾中添加任务, 无时限
     * @param element 需要添加的任务
     */
    public void put(T element) {
        lock.lock();
        try {
            // 使用 while 可以防止线程被误唤醒
            while (getSize() >= this.maxSize) {
                try {
                    LOG.debug("任务已满");
                    FULL_WAIT_SET.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOG.debug("存放任务成功");
            this.deque.addLast(element);
            EMPTY_WAIT_SET.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 向阻塞队列尾中添加任务, 无时限
     * @return 移除队首元素并返回
     */
    public T take() {
        lock.lock();
        try {
            // 使用 while 可以防止线程被误唤醒
            while (this.deque.isEmpty()) {
                try {
                    LOG.debug("队列已空, 无法获取任务");
                    EMPTY_WAIT_SET.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T first = this.deque.removeFirst();
            LOG.debug("获取任务成功: {}", first);
            FULL_WAIT_SET.signalAll();
            return first;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 向阻塞队列尾中添加任务, 有时限
     * @param element 需要添加的任务
     */
    public Boolean poll(T element, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            timeout = timeUnit.toNanos(timeout);
            // 使用 while 可以防止线程被误唤醒
            while (getSize() >= this.maxSize) {
                if (timeout <= 0) {
                    // 超过时限，添加失败
                    LOG.debug("超过时限, 添加失败");
                    return false;
                }
                try {
                    timeout = FULL_WAIT_SET.awaitNanos(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOG.debug("存放任务成功");
            this.deque.addLast(element);
            EMPTY_WAIT_SET.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 向阻塞队列尾中添加任务, 无时限
     * @return 移除队首元素并返回, 超时后返回 null
     */
    public T offer(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            timeout = timeUnit.toNanos(timeout);
            // 使用 while 可以防止线程被误唤醒
            while (this.deque.isEmpty()) {
                if (timeout <= 0) {
                    LOG.debug("超时, 获取任务失败");
                    return null;
                }
                try {
                    timeout = EMPTY_WAIT_SET.awaitNanos(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T first = this.deque.removeFirst();
            LOG.debug("获取任务成功: {}", first);
            FULL_WAIT_SET.signalAll();
            return first;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 尝试向阻塞队列中添加任务
     * @param element 需要添加的任务
     * @param reject 当阻塞队列已满时需要执行的拒绝策略
     */
    public void tryPut(T element, RejectPolicy<T> reject) {
        lock.lock();
        try {
            if (getSize() < this.maxSize) {
                LOG.debug("添加任务成功: {}", element);
                this.deque.addLast(element);
            } else {
                reject.reject(this, element);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 线程安全，获取阻塞任务队列任务个数
     * @return 阻塞队列的任务数
     */
    public Integer getSize() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }
}
