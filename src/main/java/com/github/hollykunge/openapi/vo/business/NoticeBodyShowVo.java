package com.github.hollykunge.openapi.vo.business;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: zhuqz
 * @date: 2021/3/25 14:33
 * @description: 展示通知
 */
public class NoticeBodyShowVo  extends NoticeBodyVo{
    @ApiModelProperty("业务单号")
    private String id  ;
    @ApiModelProperty("查看详情地址")
    String detailUrl     ;
    @ApiModelProperty("类型")
    String type           ;
    @ApiModelProperty("事件描述（摘要）")
    String contentAbs    ;
    @ApiModelProperty("事件详情（可扩展采用 key：value json格式）")
    String contentDetail ;
    @ApiModelProperty("图片url")
    String imgUrl        ;
    @ApiModelProperty("备注")
    String bz             ;
}