package com.bocang.task.mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

/**
 * @author LJJ
 * @version 1.0.0
 * @Description 设备功能表
 * @date 2020/12/25下午2:40
 */

@Data
@ToString
@Accessors(chain = true)
@Document(collection = "device_function")
public class DeviceProperties {

    @MongoId
    @Field(value = "id", targetType = FieldType.STRING)
    private String id;

    @Field(value = "productKey", targetType = FieldType.STRING)
    private String productKey;

    @Field(value = "deviceName", targetType = FieldType.STRING)
    private String deviceName;

    @Field(value = "functionName", targetType = FieldType.STRING)
    private String functionName;

    @Field(value = "value", targetType = FieldType.STRING)
    private String value;

    @Field(value = "createdAt", targetType = FieldType.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    @Field(value = "updatedAt", targetType = FieldType.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;


}
