package com.imoc.order.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzTest_Bean {

    @Test
    public void test(){
        try {

            //从工厂获取调度程序实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(HelloJobS.class)
                    .withIdentity("job1", "group1")
                    .build();
            // 每3秒执行一次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0/3 * * * * ?")
                    )
                    .build();
            //告诉quartz使用我们的触发器安排作业
            scheduler.scheduleJob(job, trigger);
            // and start it off

            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}