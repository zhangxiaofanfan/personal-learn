package com.zhangxiaofanfan.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 任务的实现类, 主要是任务被调度执行时的具体操作
 *
 * @author zhangxiaofanfan
 * @date 2023-10-08 09:41:07
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 创建一个事件，下面仅创建一个输出语句作演示
        System.out.println(Thread.currentThread().getName() + "--"
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
