package com.imoc.order.quartz;

import com.imoc.order.OrderApplication;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class HelloJobS implements Job {
    private QuartzService quartzService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        quartzService =  springContexUtil.applicationContext.getBean(QuartzService.class);
        String ss = quartzService.getTest();
        System.out.println(ss);
        System.out.println(quartzService);
    }
}
