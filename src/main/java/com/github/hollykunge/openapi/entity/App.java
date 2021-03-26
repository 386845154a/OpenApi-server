package com.github.hollykunge.openapi.entity;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Column;

import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2020/6/19 16:14
 * @description: app 注册表
 */
@Data
@ApiModel("应用信息")
@Table(name = "API_APP")
public class App  extends BaseEntity {
    @Column(name = "APPID")
    @ApiModelProperty("应用ID")
    private String appId;
    @Column(name = "SECRET")
    @ApiModelProperty("应用密级")
    private String secret;
    @Column(name = "STATUS")
    @ApiModelProperty("应用状态")
    private String status;
    @Column(name = "NAME")
    @ApiModelProperty("应用名称")
    private String name;
    @Column(name = "DESCRIPTION")
    @ApiModelProperty("应用描述")
    private String description;
    @Column(name = "MAIN_URL")
    @ApiModelProperty("应用地址")
    private String mainUrl;
    @Column(name = "CONTACT_NAME")
    @ApiModelProperty("联系人")
    private String contactName;
    @Column(name = "CONTACT_PHONE")
    @ApiModelProperty("联系人电话")
    private String contactPhone;
    @Column(name = "PWD")
    @ApiModelProperty("登录密码")
    private String pwd;
    @Column(name = "CODE")
    @ApiModelProperty("身份证")
    private String code;

}
