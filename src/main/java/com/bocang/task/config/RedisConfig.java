package com.bocang.task.config;

import com.bocang.task.event.RedisKeyExpiredListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author LJJ
 * @Description Redis配置
 * @date 2020/8/25 下午3:59
 * @Version 1.0
 */

@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    public RedisKeyExpiredListener redisKeyExpiredListener() {
        return new RedisKeyExpiredListener(this.redisMessageListenerContainer());
    }

}
