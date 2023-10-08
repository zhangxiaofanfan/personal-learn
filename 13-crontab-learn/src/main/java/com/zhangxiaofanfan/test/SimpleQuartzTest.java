package com.zhangxiaofanfan.test;

import com.zhangxiaofanfan.job.SimpleJob;
import com.zhangxiaofanfan.listener.MyJobListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

import java.util.concurrent.TimeUnit;

/**
 * 基于时间间隔的定时任务:
 *  基于时间间隔和时间长度实现定时任务，借助SimpleTrigger，例如这个场景——每隔2s在控制台输出线程名和当前时间，持续30s。
 *
 * @author zhangxiaofanfan
 * @date 2023-10-08 09:42:20
 */
public class SimpleQuartzTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SimpleQuartzTest simpleQuartzTest = new SimpleQuartzTest();
        simpleQuartzTest.simpleTest();
        simpleQuartzTest.cronTest();
    }


    /*
     * 基于时间间隔的定时任务
     */
    public void simpleTest() throws SchedulerException, InterruptedException {
        // 1、创建Scheduler（调度器）
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与SimpleJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();
        // 3、构建Trigger（触发器），定义执行频率和时长
        Trigger trigger = TriggerBuilder.newTrigger()
                // 指定group和name，这是唯一身份标识
                .withIdentity("trigger-1", "trigger-group")
                .startNow()  //立即生效
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(2)   // 每隔2s执行一次
                                .repeatForever()            // 永久执行
                )
                .build();
        //4、将Job和Trigger交给Scheduler调度
        scheduler.scheduleJob(jobDetail, trigger);
        // 5、启动Scheduler
        scheduler.start();
        // 休眠，决定调度器运行时间，这里设置30s
        TimeUnit.SECONDS.sleep(30);
        // 关闭Scheduler
        scheduler.shutdown();
    }

    /*
     * 基于cron表达式的定时任务
     */
    public void cronTest() throws SchedulerException, InterruptedException {
        // 1、创建Scheduler（调度器）
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与SimpleJob类绑定
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job-1", "job-group").build();
        // 3、构建Trigger（触发器），定义执行频率和时长
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-1", "trigger-group")
                .startNow()  //立即生效
                .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 *"))
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
        // 休眠，决定调度器运行时间，这里设置30s
        TimeUnit.SECONDS.sleep(30);
        // 关闭Scheduler
        scheduler.shutdown();
    }
}
