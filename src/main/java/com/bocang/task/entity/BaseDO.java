package com.bocang.task.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LJJ
 * @version 1.0
 * @Description
 * @date 2020/8/26 上午9:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDO implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    protected Long id;

    @ApiModelProperty(value = "注册时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    protected Date createdAt;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    protected Date updatedAt;

}
