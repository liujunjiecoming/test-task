package com.bocang.task.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LJJ
 * @version 1.0.0
 * @Description TODO
 * @date 2021/3/31 上午10:14
 */

@Slf4j
@Configuration
@EnableWebSocketMessageBroker // 注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用
@MessageMapping
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 将clientMessage注册为STOMP的一个端点
        // 客户端在订阅或发布消息到目的路径前，要连接该端点
        // setAllowedOrigins允许所有域连接，否则浏览器可能报403错误
        registry.addEndpoint("/websocket").setAllowedOrigins("*").addInterceptors().withSockJS(); //
    }

    /**
     * 推送日志到/topic/pullLogger
     */
    @PostConstruct
    public void pushLogger() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Runnable fileLog = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String log = LoggerQueue.getInstance().poll().toString();
                        System.out.println("log: " + log);
                        if (log != null) {
                            if (messagingTemplate != null)
                                messagingTemplate.convertAndSend("/topic/pullFileLogger", log);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(fileLog);
        executorService.submit(fileLog);
    }
}
