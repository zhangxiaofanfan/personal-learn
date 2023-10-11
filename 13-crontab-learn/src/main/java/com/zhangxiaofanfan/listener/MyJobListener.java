package com.zhangxiaofanfan.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * 任务监听器
 *
 * @author zhangxiaofanfan
 * @date 2023-10-08 10:48:22
 */
public class MyJobListener implements JobListener {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job " + jobName + " 将要被执行时");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job " + jobName + " 即将被执行，但又被TriggerListener否决时会调用该方法");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job " + jobName + " 被执行之后调用这个方法");
        if (e != null) {
            System.out.println("Exception occurred during execution: " + e.getMessage());
        }
    }
}

