package com.bocang.task.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午3:02 20-10-20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MessageCenterVO", description = "消息中心")
public class MessageCenterVO {

    private Long userId;

    private Long deviceId;

    private Integer msgType;

    private Integer msgTitle;

    private String msgContent;

    private String titlePic;


}
