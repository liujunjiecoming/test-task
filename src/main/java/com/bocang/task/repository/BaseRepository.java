package com.bocang.task.repository;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午3:42 20-10-20
 */

@Component
public class BaseRepository {


    @Qualifier("elasticsearchClient")
    @Autowired
    private RestHighLevelClient restHighLevelClient;




}
