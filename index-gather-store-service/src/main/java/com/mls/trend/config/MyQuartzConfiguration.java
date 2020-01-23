package com.mls.trend.config;

import com.mls.trend.util.IndexDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyQuartzConfiguration  {
    private static final int interval=1;

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
                .withIntervalInMinutes(interval).repeatForever();    //每分钟刷新
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("indexDataSyncTrigger").withSchedule(scheduleBuilder).build();
    }
}
