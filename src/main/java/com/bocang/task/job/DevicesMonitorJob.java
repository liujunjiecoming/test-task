package com.bocang.task.job;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bocang.task.entity.BCDevicesDO;
import com.bocang.task.service.BCDeviceService;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author LJJ
 * @version 1.0
 * @Description 设备监控器
 * @date 下午3:11 20-9-22
 */

@Log4j2
public class DevicesMonitorJob implements Job {

    @Autowired
    private BCDeviceService bcDeviceService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //todo 逻辑任务
//        List<BCDevicesDO> devicesList = bcDeviceService.list(Wrappers.<BCDevicesDO>lambdaQuery().eq(BCDevicesDO::getStatus, "1"));
//        log.info(devicesList);

        log.info("HelloWorld " + new Date());
    }


}
