package com.bocang.task.job;

import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午4:24 20-11-14
 */

@Log4j2
public class RefreshTokenJob implements Job {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("刷新token");
        Set<String> keys = redisTemplate.keys("token:*");
        if (keys != null && keys.size() != 0) {
            for (String key : keys) {
                redisTemplate.expire(key, 7200L, TimeUnit.SECONDS);
            }
        }
    }
}
