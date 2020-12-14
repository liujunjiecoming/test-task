package com.bocang.task;

import com.alibaba.fastjson.JSON;
import com.bocang.task.entity.DictionaryDO;
import com.bocang.task.service.DictionaryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskApplicationTests {

    @Autowired
    private DictionaryService dictionaryService;


    @Test
    void contextLoads() {
        List<DictionaryDO> list = dictionaryService.list();
        System.out.println(JSON.toJSONString(list));
    }


}
