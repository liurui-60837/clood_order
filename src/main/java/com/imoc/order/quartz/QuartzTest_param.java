package com.imoc.order.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest_param {

    public static void main(String[] args) {
        try {
            //从工厂获取调度程序实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(HelloJob_param.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("JobDetail_key","12333")
                    .build();
            // 立即触发作业运行，然后每 40 秒重复一次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .usingJobData("trigger_key","1112")
                    .startNow()
                    .withSchedule( CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                    .build();
            //告诉quartz使用我们的触发器安排作业
            scheduler.scheduleJob(job, trigger);
            // and start it off

            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}