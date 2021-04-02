package com.bocang.task.repository;

import com.bocang.task.mongodb.DeviceProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author LJJ
 * @version 1.0.0
 * @Description
 * @date 2020/12/25下午2:52
 */
public interface DeviceFunctionRepository extends MongoRepository<DeviceProperties, String> {
}
