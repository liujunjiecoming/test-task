package com.bocang.task;

import com.alibaba.fastjson.JSON;
import com.bocang.task.mongodb.DeviceProperties;
import com.bocang.task.repository.DeviceFunctionRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DeviceFunctionRepository deviceFunctionRepository;

    @Test
    void contextLoads() {
        System.out.println(redisTemplate.opsForSet().isMember("bind:device", "a1ROSZJYEnb:7C25DA0C75E4"));
//        redisTemplate.opsForSet().remove("bind:device", "a1ROSZJYEnb:7C25DA0C75E4");
    }

    // 1查询
    private void query() {
        Criteria criteria1 = Criteria.where("productKey").is("a1DdjaHCK89");
        Criteria criteria2 = Criteria.where("deviceName").is("HqR9BP8dzKwBsKadJ4Vd");
        Criteria criteria3 = Criteria.where("functionName").is("Brightness");

        Query query = new Query(new Criteria().andOperator(criteria1, criteria2));

        System.out.println(mongoTemplate.count(query, DeviceProperties.class));
        System.out.println(JSON.toJSON(mongoTemplate.find(query, DeviceProperties.class)));
    }

    // 2更新
    private void update() {
        Criteria criteria1 = Criteria.where("productKey").is("a1DdjaHCK89");
        Criteria criteria2 = Criteria.where("deviceName").is("HqR9BP8dzKwBsKadJ4Vd");
//        Criteria criteria3 = Criteria.where("functionName").is("Brightness");
        Query query = new Query(new Criteria().andOperator(criteria1, criteria2));

        Update update = new Update().set("value", "300");

        UpdateResult result = mongoTemplate.updateMulti(query, update, DeviceProperties.class);
        System.out.println(JSON.toJSON(result));
    }

    // 3聚合查询
    private void aggQuery() {
        SortOperation sort = Aggregation.sort(Sort.by("pkCount").descending());
        GroupOperation group = Aggregation.group("productKey").count().as("pkCount");
        Aggregation agg = Aggregation.newAggregation(group, sort);
        AggregationResults<Map> results = mongoTemplate.aggregate(agg, DeviceProperties.class, Map.class);

        for (Map mappedResult : results.getMappedResults()) {
            System.out.println(JSON.toJSON(mappedResult));
        }
    }


}
