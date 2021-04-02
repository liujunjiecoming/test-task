package com.bocang.task.model;

import com.bocang.task.constant.ConfigConsts;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author LJJ
 * @version 1.0
 * @Description
 * @date 2020/8/26 上午8:45
 */
@ApiModel
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@EqualsAndHashCode(callSuper = false)
public class JsonVO implements Serializable {

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "接口状态码", required = true)
    private Integer code;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "接口返回数据")
    private Object data;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "接口错误信息")
    private String msg;

    public JsonVO() {
    }

    public JsonVO(int code) {
        this.code = code;
    }

    public JsonVO(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public Boolean isSuccess() {
        return this.code == 100;
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static JsonVO success(Object data) {
        return new JsonVO(100, data, ConfigConsts.getErrorMsg(100));
    }

    /**
     * 成功
     *
     * @return
     */
    public static JsonVO success() {
        return new JsonVO(100, ConfigConsts.getErrorMsg(100));
    }

    /**
     * 失败
     *
     * @return
     */
    public static JsonVO failure() {
        return new JsonVO(101, ConfigConsts.getErrorMsg(101));
    }

    /**
     * 失败
     *
     * @param code
     * @return
     */
    public static JsonVO failure(int code) {
        return new JsonVO(code, ConfigConsts.getErrorMsg(code));
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static JsonVO failure(int code, String msg) {
        return new JsonVO(code, msg);
    }

    /**
     * 失败
     *
     * @param errorMsg
     * @return
     */
    public static JsonVO failure(String errorMsg) {
        return new JsonVO(101, errorMsg);
    }

    @Override
    public String toString() {
        return "JsonVO{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}