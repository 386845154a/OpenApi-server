package com.github.hollykunge.openapi.entity.business;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/3/22 15:10
 * @description:协议内容-消息展示
 */
@Data
@ApiModel("通知消息体数据展示")
@Table(name = "BUSINESS_NOTICE_BODY_SHOW")
public class NoticeBodyShow  extends BaseEntity {
    @Column(name = "DETAIL_URL")
    @ApiModelProperty("查看详情地址")
    String detailUrl     ;
    @Column(name = "TYPE")
    @ApiModelProperty("类型")
    String type           ;
    @Column(name = "CONTENT_ABS")
    @ApiModelProperty("事件描述（摘要）")
    String contentAbs    ;
    @Column(name = "CONTENT_DETAIL")
    @ApiModelProperty("事件详情（可扩展采用 key：value json格式）")
    String contentDetail ;
    @Column(name = "IMG_URL")
    @ApiModelProperty("图片url")
    String imgUrl        ;
    @Column(name = "BZ")
    @ApiModelProperty("备注")
    String bz             ;
}
