package com.zhangxiaofanfan.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 向容器中注入线程池对象
 *
 * @author zhangxiaofanfan
 * @date 2023-07-18 15:55:05
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //线程名称
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("schedule-thread-").build();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5, namedThreadFactory);
        executor.setMaximumPoolSize(100);
        executor.setKeepAliveTime(300, TimeUnit.SECONDS);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskRegistrar.setScheduler(executor);
    }

}