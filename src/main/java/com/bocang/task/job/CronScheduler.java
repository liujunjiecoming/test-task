package com.bocang.task.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author LJJ
 * @version 1.0
 * @Description
 * @date 下午3:44 20-9-22
 */

@Component
public class CronScheduler {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private void devicesMonitorSchedule(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(DevicesMonitorJob.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/15 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("name1", "name1")
                .withSchedule(scheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    private void refreshMonitorSchedule(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(RefreshTokenJob.class).withIdentity("job2", "group2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 * * * ?");
        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger2", "group2")
                .usingJobData("name2", "name2")
                .withSchedule(scheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        devicesMonitorSchedule(scheduler);
//        refreshMonitorSchedule(scheduler);
    }
}
