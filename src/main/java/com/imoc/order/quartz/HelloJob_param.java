package com.imoc.order.quartz;

import org.quartz.*;

import java.util.Date;

public class HelloJob_param implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail j =  jobExecutionContext.getJobDetail();
        Trigger t=  jobExecutionContext.getTrigger();

        System.out.println("当前时间 "+DateUtil.dateF(new Date())+"  当前线程："+Thread.currentThread().getName());
        System.out.println(j.getJobDataMap().get("JobDetail_key"));
    }
}
