package com.github.hollykunge.openapi.entity.business;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/3/22 15:04
 * @description: 操作协议
 */
@Data
@ApiModel("通知消息体审批")
@Table(name = "BUSINESS_NOTICE_BODY_OPERATE")
public class NoticeBodyOperate extends BaseEntity {
   @Column(name = "BUSINESS_ID")
   @ApiModelProperty("业务单号")
   private String businessId  ;
    @Column(name = "DETAIL_URL")
    @ApiModelProperty("业务详情")
   private String detailUrl  ;
   @Column(name = "TYPE")
   @ApiModelProperty("类型")
   private String type        ;
   @Column(name = "CONTENT_ABS")
   @ApiModelProperty("事件描述（摘要）")
   private String contentAbs ;
   @Column(name = "CALL_BACK")
   @ApiModelProperty("同意、驳回接口，回调地址，参数固定")
   private String callBack   ;
   @Column(name = "MSG")
   @ApiModelProperty("理由回填（同意、驳回）")
   private String msg         ;
   @Column(name = "IMG_URL")
   @ApiModelProperty("图片地址")
   private String imgUrl     ;
   @Column(name = "BZ")
   @ApiModelProperty("备注")
   private String bz          ;
}