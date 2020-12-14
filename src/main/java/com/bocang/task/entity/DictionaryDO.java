package com.bocang.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LJJ
 * @version 1.0
 * @Description 系统字典信息
 * @date 2020/8/28 上午10:05
 */

@ApiModel(value = "DictionaryDO", description = "系统字典信息")
@TableName(value = "dictionary")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryDO extends BaseDO {

    @ApiModelProperty(value = "字典名称")
    @TableField("dict_name")
    private String dictName;


    @ApiModelProperty(value = "字典值")
    @TableField("dict_value")
    private String dictValue;


    @ApiModelProperty(value = "删除时间")
    @TableField("gmt_delete")
    private Date gmtDelete;


    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    private Boolean deleted;


    @ApiModelProperty(value = "是否显示")
    @TableField("is_enable")
    private Boolean enable;


    @ApiModelProperty(value = "父类编号")
    @TableField("parent_id")
    private Long parentId;


    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @Override
    public String toString() {
        return "DictionaryDO{" +
                "dictName='" + dictName + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", gmtDelete=" + gmtDelete +
                ", deleted=" + deleted +
                ", enable=" + enable +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
