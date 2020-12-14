package com.bocang.task.init;

import com.bocang.task.job.CronScheduler;
import lombok.extern.log4j.Log4j2;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author LJJ
 * @version 1.0
 * @Description 初始化资源
 * @date 2020/8/25 下午5:26
 */
@Log4j2
@Component
public class ResourceRunner implements CommandLineRunner {

    @Autowired
    private CronScheduler cronScheduler;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @Override
    public void run(String... args) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //scheduler.deleteJob(JobKey.jobKey("job1", "group1"));
        cronScheduler.scheduleJobs();
        log.info("====== 系统错误代码加载完成 ======");
    }


}
