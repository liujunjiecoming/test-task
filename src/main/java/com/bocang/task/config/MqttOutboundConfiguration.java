package com.bocang.task.config;

import com.bocang.task.constant.ConfigConsts;
import com.bocang.task.mqtt.MqttGateway;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author zizi
 * @Version 1.0
 * @Description 消息发送配置类
 * @Date 2020/9/7 14:59
 */
@Configuration
@Slf4j
public class MqttOutboundConfiguration {

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static String inboundClientId;

    private static String outPoundClientId;

    static {
        //clientId不能重复所以这里我设置为系统时间
        inboundClientId = String.valueOf(System.currentTimeMillis());
        outPoundClientId = String.valueOf(System.currentTimeMillis() + 1);
    }

    /**
     * 连接设置
     *
     * @return
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setKeepAliveInterval(2);
        mqttConnectOptions.setWill("/device/offline", "offline".getBytes(), 1, false);
        return mqttConnectOptions;
    }

    /**
     * 客户端工厂
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    /**
     * 发布通知
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        //clientId不能重复所以这里我设置为系统时间
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(outPoundClientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(false);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }

    /**
     * 发布通道为直连
     *
     * @return
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * 接收通道
     *
     * @return
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound(MqttPahoClientFactory mqttPahoClientFactory) {
        Set<String> topics = redisTemplate.opsForSet().members(ConfigConsts.DEVICE_TOPIC);
//        MqttPahoMessageDrivenChannelAdapter adapter =
//                new MqttPahoMessageDrivenChannelAdapter(hostUrl, inboundClientId, mqttPahoClientFactory, topics.toArray(new String[topics.size()]));
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(hostUrl, inboundClientId, mqttPahoClientFactory, "message_arrived");
        adapter.setCompletionTimeout(50000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                Object topic = message.getHeaders().get("mqtt_receivedTopic");
                log.info("设备接收topic: " + topic);
                log.info("设备mqtt收到消息: " + message.getPayload());

                HashMap<String, String> map = Maps.newHashMap();

                mqttGateway.sendToMqtt("测试消息", String.valueOf(topic) + "_reply");
            }
        };
    }
}
