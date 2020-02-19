package com.mls.trend.config;

import com.mls.trend.util.IndexDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyQuartzConfiguration  {
    private static final int interval=24;               //设置定时器刷新间隔(小时)

//    注册定时器
    @Bean
    public JobDetail weatherDataSyncJobDetail(){
        return JobBuilder.newJob(IndexDataSyncJob.class).withIdentity("indexDataSyncJob")
                .storeDurably().build();
    }

//    注册定时器触发器
    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(interval).repeatForever();    //每24小时刷新
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("indexDataSyncTrigger").withSchedule(scheduleBuilder).build();
    }
}
