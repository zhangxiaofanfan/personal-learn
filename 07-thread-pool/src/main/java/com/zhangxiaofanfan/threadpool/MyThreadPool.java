package com.zhangxiaofanfan.threadpool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午12:58
 * @description 自定义线程池实现类
 */
public class MyThreadPool {
    private Integer coreSize;
    private RejectPolicy<Runnable> reject;
    private MyBlockQueue<Runnable> taskQueue;
    private final Set<Worker> workers = new HashSet<>();

    public MyThreadPool(Integer coreSize, Integer taskSize, RejectPolicy<Runnable> reject) {
        this.taskQueue = new MyBlockQueue<>(taskSize);
        this.coreSize = coreSize;
        this.reject = reject;
    }

    /**
     * 自定义线程池提交任务函数
     * @param task 需要自定义线程池执行的任务
     */
    public void execute(Runnable task) {
        synchronized (workers) {
            // 还有多余线程可以执行任务
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                this.taskQueue.put(task);
            }
        }
    }

    /**
     *  Worker: 用来执行任务的线程
     */
    class Worker extends Thread {
        // 需要线程执行的任务
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行 execute 提交来的任务，当提交来的任务被执行完毕时，执行阻塞队列中的任务
            while (task != null || (task = taskQueue.offer(1, TimeUnit.SECONDS)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            // 表示提交给此线程的任务已经执行结束, 并且阻塞队列中已经没有可执行的任务
            synchronized (workers) {
                workers.remove(this);
            }
        }
    }
}
