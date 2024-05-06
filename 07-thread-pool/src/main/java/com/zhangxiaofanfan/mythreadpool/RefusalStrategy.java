package com.zhangxiaofanfan.mythreadpool;


@FunctionalInterface
public interface RefusalStrategy<T> {
    /**
     * 当阻塞队列满时调用的拒绝策略
     *
     * @param queue 阻塞队列
     * @param task 提交的任务
     */
    void reject(MyBlockQueue<T> queue, T task);
}
