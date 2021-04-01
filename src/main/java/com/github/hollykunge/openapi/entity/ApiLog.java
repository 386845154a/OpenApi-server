package com.github.hollykunge.openapi.entity;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/4/1 13:50
 * @description:
 */
@Data
@ApiModel("API 日志")
@Table(name = "API_LOG")
public class ApiLog   extends BaseEntity {
    @Column(name = "APPID")
    @ApiModelProperty("应用ID")
    private String appId;
    @Column(name = "SERVICE_ID")
    @ApiModelProperty("服务id")
    private String serviceId;
    @Column(name = "TIME")
    @ApiModelProperty("调用时间")
    private String time;
    @Column(name = "RES")
    @ApiModelProperty("调用结果1成功0失败")
    private String res;
    @Column(name = "MSG")
    @ApiModelProperty("描述")
    private String msg;
    @Column(name = "PARAM")
    @ApiModelProperty("参数")
    private String param;
    @Column(name = "BODY")
    @ApiModelProperty("请求体")
    private String body;
    @Column(name = "RETURN_OBJ")
    @ApiModelProperty("返回结果")
    private String returnObj;
}
