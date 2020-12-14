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
 * @Description 设备
 * @date 2020/8/26 下午5:21
 */

@ApiModel(value = "BCDevicesDO", description = "设备")
@TableName(value = "bc_devices")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BCDevicesDO extends BaseDO {

    @ApiModelProperty(value = "设备名称")
    @TableField("device_name")
    private String deviceName;

    @ApiModelProperty(value = "产品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "固件版本")
    @TableField("version")
    private String version;

    @ApiModelProperty(value = "mac地址")
    @TableField("mac")
    private String mac;

    @ApiModelProperty(value = "最近上线时间")
    @TableField("online_time")
    private Date onlineTime;

    @ApiModelProperty(value = "最近离线时间")
    @TableField("offline_time")
    private Date offTime;

    @ApiModelProperty(value = "激活时间")
    @TableField("activation_time")
    private Date activationTime;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @Override
    public String toString() {
        return "BCDevicesDO{" +
                "deviceName='" + deviceName + '\'' +
                ", productId=" + productId +
                ", status=" + status +
                ", version='" + version + '\'' +
                ", mac='" + mac + '\'' +
                ", onlineTime=" + onlineTime +
                ", offTime=" + offTime +
                ", activationTime=" + activationTime +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", createAt=" + createdAt +
                ", updateAt=" + updatedAt +
                '}';
    }
}
