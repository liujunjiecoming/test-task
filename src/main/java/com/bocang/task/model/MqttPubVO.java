package com.bocang.task.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午3:39 20-11-10
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MqttPubVO", description = "消息发送")
public class MqttPubVO {

    private String data;

    private String topic;

}
