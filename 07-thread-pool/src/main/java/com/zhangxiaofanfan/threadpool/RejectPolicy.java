package com.zhangxiaofanfan.threadpool;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午1:04
 * @description TODO
 */
@FunctionalInterface
public interface RejectPolicy<T> {
    /**
     * 当任务队列已满并且有新的任务到达时，需要执行的策略，这样可以将拒绝策略交由用户自定义
     * @param blockQueue 阻塞队列
     * @param element 任务
     */
    void reject(MyBlockQueue<T> blockQueue, T element);
}
