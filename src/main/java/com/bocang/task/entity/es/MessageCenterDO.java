package com.bocang.task.entity.es;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LJJ
 * @version 1.0
 * @Description 消息中心
 * @date 上午10:38 20-9-24
 */

@Data
@Document(indexName = "message_center")
public class MessageCenterDO implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Long, value = "user_id")
    private Long userId;

    @Field(type = FieldType.Long, value = "device_id")
    private Long deviceId;

    @Field(type = FieldType.Keyword, value = "msg_type")
    private Integer msgType;

    @Field(type = FieldType.Keyword, value = "msg_title")
    private Integer msgTitle;

    @Field(type = FieldType.Text, value = "msg_content")
    private String msgContent;

    @Field(type = FieldType.Keyword, value = "title_pic")
    private String titlePic;

    @Field(type = FieldType.Date, value = "created_at", format = DateFormat.custom, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @Field(type = FieldType.Date, value = "updated_at", format = DateFormat.custom, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;


}
