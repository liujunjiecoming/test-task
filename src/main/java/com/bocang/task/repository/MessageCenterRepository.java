package com.bocang.task.repository;

import com.bocang.task.entity.es.MessageCenterDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午2:44 20-10-20
 */
public interface MessageCenterRepository extends ElasticsearchRepository<MessageCenterDO, Long> {
}
