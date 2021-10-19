package com.imoc.order.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("当前时间 "+DateUtil.dateF(new Date())+"  当前线程："+Thread.currentThread().getName());
    }
}
