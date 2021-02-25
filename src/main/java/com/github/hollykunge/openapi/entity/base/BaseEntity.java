package com.github.hollykunge.openapi.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @description: 实体基类
 * @author: dd
 * @since: 2019-05-16
 */
@Data
@ApiModel("实体基类")
public class BaseEntity {

    @Id
    @Column(name = "Id")
    @ApiModelProperty("系统内部唯一id")
    private String id;

    @ApiModelProperty("创建时间")
    @Column(name = "CRT_TIME")
    private Date crtTime;

    @ApiModelProperty("创建用户id")
    @Column(name = "CRT_USER")
    private String crtUser;

    @ApiModelProperty("创建用户名称")
    @Column(name = "CRT_NAME")
    private String crtName;

    @ApiModelProperty("创建用户ip")
    @Column(name = "CRT_HOST")
    private String crtHost;

    @ApiModelProperty("更新时间")
    @Column(name = "UPD_TIME")
    private Date updTime;

    @ApiModelProperty("更新用户id")
    @Column(name = "UPD_USER")
    private String updUser;

    @ApiModelProperty("更新用户名称")
    @Column(name = "UPD_NAME")
    private String updName;

    @ApiModelProperty("创建用户ip")
    @Column(name = "UPD_HOST")
    private String updHost;

    @ApiModelProperty("备用1")
    @Column(name = "ATTR1")
    private String attr1;

    @ApiModelProperty("备用2")
    @Column(name = "ATTR2")
    private String attr2;

    @ApiModelProperty("备用3")
    @Column(name = "ATTR3")
    private String attr3;

    @ApiModelProperty("备用4")
    @Column(name = "ATTR4")
    private String attr4;
}
