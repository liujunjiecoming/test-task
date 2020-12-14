package com.bocang.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 下午2:37 20-12-3
 */

@RestController
public class TestController {

    @RequestMapping("/hello")
    public Object hello(){
        System.out.println("傻逼吗");
        return "Hello Docker";
    }
}
