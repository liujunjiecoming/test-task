package com.bocang.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bocang.task.entity.es.MessageCenterDO;
import com.bocang.task.model.JsonVO;
import com.bocang.task.model.MessageCenterVO;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午3:00 20-10-20
 */
public interface MessageCenterService extends IService<MessageCenterDO> {

    JsonVO add(MessageCenterVO messageCenter);


}
