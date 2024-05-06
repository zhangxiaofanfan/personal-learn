package com.zhangxiaofanfan.juclearn.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义线程池设计
 *
 * @author zhangxiaofanfan
 * @date 2024-01-25 12:18:30
 */
@Slf4j
public class MyThreadPool {
    public static void main(String[] args) {

        // 拒绝策略1: 死等
        RejectPolicy<Runnable> reject1 = BlockingDeque::put;
        // 拒绝策略2: 超时等待
        RejectPolicy<Runnable> reject2 = (queue, task) -> queue.offer(task, 500, TimeUnit.MILLISECONDS);
        // 拒绝策略3: 让调用者放弃执行任务
        RejectPolicy<Runnable> reject3 = (queue, task) -> log.error("阻塞队列已满, 拒绝执行当前任务, 任务为: {}", task);
        // 拒绝策略4: 让调用者抛出异常
        RejectPolicy<Runnable> reject4 = (queue, task) -> {throw new RuntimeException(String.format("阻塞队列已满, 向外排除异常, 任务为: %s", task));};
        // 拒绝策略5: 让调用者自己执行
        RejectPolicy<Runnable> reject5 = (queue, task) -> task.run();


        ThreadPool threadPool = new ThreadPool(2, 2000, TimeUnit.MILLISECONDS, 5, reject2);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("任务被执行, 任务序号为: {}", finalI);
            });
        }
    }
}

/**
 * 自定义线程池
 */
@Slf4j
class ThreadPool {
    private final Integer coreSize;
    private final Set<Worker> workers = new HashSet<>();
    private final BlockingDeque<Runnable> queue;
    private final Long timeout;
    private final TimeUnit unit;
    private final RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(Integer coreSize, long timeout, TimeUnit unit, Integer queueSize, RejectPolicy<Runnable> rejectPolicy) {
        this.unit = unit;
        this.timeout = timeout;
        this.coreSize = coreSize;
        this.queue = new BlockingDeque<>(queueSize);
        this.rejectPolicy = rejectPolicy;
    }

    /**
     * 执行线程任务
     *
     * @param task 线程任务
     */
    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                // 加入队列时, 可以将拒绝策略也传递过去, 实现拒绝策略的下放
                // queue.put(task);
                queue.tryPut(task, this.rejectPolicy);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行当前任务, 执行完当前任务之后获取阻塞队列中的任务
            // while (task != null || (task = queue.take()) != null) {
            while (task != null || (task = queue.poll(timeout, unit)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    log.info("任务执行过程中出现异常, 异常信息为: {}", e.getMessage());
                }
                task = null;
            }
            // 执行完毕并退出
            synchronized (workers) {
                log.info("线程正常退出, 退出线程对象为: {}", this);
                workers.remove(this);
            }
        }
    }
}

/**
 * 自定义阻塞队列
 *
 * @param <T> 阻塞队列保存的数据对象
 */
@Slf4j
class BlockingDeque<T> {
    /**
     * 用来保存任务使用的队列
     */
    private final Deque<T> deque = new ArrayDeque<>();
    /**
     * LOCK 会有多个线程对阻塞队列进行生产和消费操作, 所以需要进行加锁才能进行操作
     */
    private final ReentrantLock LOCK = new ReentrantLock();
    /**
     * FULL_CONDITION 阻塞队列满时会进入的条件变量, 主要针对生产者线程
     */
    private final Condition FULL_WAIT_SET = LOCK.newCondition();
    /**
     * EMPTY_CONDITION 阻塞队列空时会进入的条件变量, 主要针对消费者线程
     */
    private final Condition EMPTY_WAIT_SET = LOCK.newCondition();

    private final Integer capacity;

    public BlockingDeque(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 向阻塞队列中添加任务
     *
     * @param element 待添加的任务
     */
    public void put(T element) {
        LOCK.lock();
        try {
            while (this.size() >= capacity) {
                try {
                    FULL_WAIT_SET.await();
                } catch (InterruptedException e) {
                    log.warn("等待过程被打断, 异常消息为: {}", e.getMessage());
                    return ;
                }
            }
            this.deque.addLast(element);
            log.info("添加任务成功, 任务: {}, 阻塞队列大小为: {}", element, this.size());
            EMPTY_WAIT_SET.signalAll();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 带有超时时限的向阻塞队列中添加任务
     *
     * @param element 任务
     * @param timeout 超时时间
     * @param unit 超时单位
     * @return 是否在规定时间内添加任务成功
     */
    public boolean offer(T element, long timeout, TimeUnit unit) {
        LOCK.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (this.size() >= capacity) {
                try {
                    if (nanos <= 0) {
                        log.warn("保存任务超时失败, 任务为: {}", element);
                        return false;
                    }
                    nanos = FULL_WAIT_SET.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.warn("等待过程被打断, 异常消息为: {}", e.getMessage());
                    return false;
                }
            }
            this.deque.addLast(element);
            log.info("添加任务成功, 任务: {}, 阻塞队列大小为: {}", element, this.size());
            EMPTY_WAIT_SET.signalAll();
            return true;
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 从阻塞队列中获取任务
     *
     * @return 获取到的任务
     */
    public T take() {
        LOCK.lock();
        try {
            while (this.size() <= 0) {
                log.debug("阻塞队列为空, 暂时无法获取任务, 进入满队列中进行等待");
                try {
                    EMPTY_WAIT_SET.await();
                } catch (InterruptedException e) {
                    log.warn("等待过程被打断, 异常消息为: {}", e.getMessage());
                }
            }
            T first = deque.removeFirst();
            log.info("获取任务成功, 任务: {}, 阻塞队列大小为: {}", first, this.size());
            FULL_WAIT_SET.signalAll();
            return first;
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 带有时限的超时获取
     *
     * @param timeout 超时时限
     */
    public T poll(long timeout, TimeUnit unit) {
        LOCK.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (deque.isEmpty()) {
                if (nanos <= 0) {
                    log.info("阻塞队列等待获取任务超时");
                    return null;
                }
                try {
                    nanos = EMPTY_WAIT_SET.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.info("空队列等待被中断, 打印异常信息: {}", e.getMessage());
                    return null;
                }
            }
            T task = deque.removeFirst();
            log.info("获取任务对象为: {}", task);
            FULL_WAIT_SET.signalAll();
            return task;
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 获取阻塞队列的大小
     *
     * @return 当前情况下阻塞队列的大小
     */
    public int size() {
        LOCK.lock();
        try {
            return this.deque.size();
        } finally {
            LOCK.unlock();
        }
    }

    public void tryPut(T task, RejectPolicy<T> rejectPolicy) {
        LOCK.lock();
        try {
            if (deque.size() < this.capacity) {
                // 表示队列未满, 正常添加任务即可
                this.deque.addLast(task);
                log.info("添加任务成功, 任务: {}, 阻塞队列大小为: {}", task, this.size());
                EMPTY_WAIT_SET.signalAll();
            } else {
                log.info("阻塞队列已满, 执行拒绝策略");
                rejectPolicy.reject(this, task);
            }
        } finally {
            LOCK.unlock();
        }
    }
}


/**
 * 拒绝策略
 *
 * @param <T> 任务类型
 */
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingDeque<T> queue, T task);
}
