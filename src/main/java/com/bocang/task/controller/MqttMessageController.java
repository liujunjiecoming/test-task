package com.bocang.task.controller;

import com.bocang.task.model.JsonVO;
import com.bocang.task.model.MqttPubVO;
import com.bocang.task.mqtt.MqttGateway;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zizi
 * @Version 1.0
 * @Description http接口触发mqtt消息分发
 * @Date 2020/9/7 15:10
 */
@RestController
@RequestMapping("/mqtt")
@Api(value = "/mqtt", tags = "实现mqtt消息发布")
public class MqttMessageController {
    @Autowired
    private MqttGateway mqttGateway;


    /**
     * sendData 消息
     * topic 订阅主题
     **/
    @PostMapping(value = "/sendMqtt")
    @ApiOperation(value = "/sendMqtt", httpMethod = "POST", response = JsonVO.class, notes = "mqtt消息推送")
    public JsonVO sendMqtt(@RequestBody MqttPubVO pub) {
//        mqttGateway.sendToMqtt(pub.getData(), pub.getTopic());
        return JsonVO.success();
    }

}
