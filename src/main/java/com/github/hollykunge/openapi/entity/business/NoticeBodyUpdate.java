package com.github.hollykunge.openapi.entity.business;
import com.github.hollykunge.openapi.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/3/22 15:28
 * @description: 通知消息体 数据升级
 */
@Data
@ApiModel("通知消息体数据更新")
@Table(name = "BUSINESS_NOTICE_BODY_UPDATE")
public class NoticeBodyUpdate  extends  BaseEntity{

    @Column(name = "PROJECT_NAME")
    @ApiModelProperty("项目名称")
    private String projectName ;
    @Column(name = "PROJECT_ID")
    @ApiModelProperty("项目id（回调查看项目相关信息使用）")
    private String projectId   ;
    @Column(name = "POST_DATA")
    @ApiModelProperty("原始数据")
    private String postData    ;
    @Column(name = "CHANGE_DATA")
    @ApiModelProperty("变更后数据")
    private String changeData  ;
    @Column(name = "VERSION")
    @ApiModelProperty("数据版本（最新）")
    private String version      ;
    @Column(name = "CORRELATION")
    @ApiModelProperty("关联性（强1、弱0关联）")
    private String correlation  ;
    @Column(name = "BZ")
    @ApiModelProperty("备注")
    private String bz           ;

}