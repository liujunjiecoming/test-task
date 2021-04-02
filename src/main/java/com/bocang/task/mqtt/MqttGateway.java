package com.bocang.task.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Author zizi
 * @Version 1.0
 * @Description 消息发送service，可以直接调用来发送消息
 * @Date 2020/9/7 15:09
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void sendToMqtt(String data);

    void sendToMqtt(String payload, @Header(MqttHeaders.TOPIC) String topic);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
