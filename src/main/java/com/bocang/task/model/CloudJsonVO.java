package com.bocang.task.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ljj
 * @version 1.0
 * @Description 调用云端接口返回参数
 * @date 下午5:19 20-11-20
 */

@ApiModel
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@EqualsAndHashCode(callSuper = false)
public class CloudJsonVO {

    private Long id;

    private String data;

    private String code;

}
