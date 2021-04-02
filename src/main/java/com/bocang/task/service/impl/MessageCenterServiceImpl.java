package com.bocang.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocang.task.dao.MessageCenterDao;
import com.bocang.task.entity.es.MessageCenterDO;
import com.bocang.task.model.JsonVO;
import com.bocang.task.model.MessageCenterVO;
import com.bocang.task.service.MessageCenterService;
import org.springframework.stereotype.Service;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午3:01 20-10-20
 */
@Service
public class MessageCenterServiceImpl extends ServiceImpl<MessageCenterDao, MessageCenterDO> implements MessageCenterService {

    @Override
    public JsonVO add(MessageCenterVO messageCenter) {
        return null;
    }
}
