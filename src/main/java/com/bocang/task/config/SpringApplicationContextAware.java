package com.bocang.task.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author LJJ
 * @version 1.0
 * @Description
 * @date 2020/8/25 下午5:57
 */
@Component
public class SpringApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContextAware.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName, Class<T> cls) {
        if (applicationContext.containsBean(beanName)) {
            return applicationContext.getBean(beanName, cls);
        } else {
            return null;
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> baseType) {
        return applicationContext.getBeansOfType(baseType);
    }
}