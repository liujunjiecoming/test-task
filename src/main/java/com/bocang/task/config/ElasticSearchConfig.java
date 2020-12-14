package com.bocang.task.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午3:19 20-10-20
 */
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {


    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
        return RestClients.create(configuration).rest();
    }


}
