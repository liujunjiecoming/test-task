package com.bocang.task.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * @author LJJ
 * @version 1.0
 * @Description Redis key过期监控器
 * @date 2020/8/25 下午4:02
 */
@Log4j2
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info(String.format("======= %s : %s ========", channel, key));
    }

}
