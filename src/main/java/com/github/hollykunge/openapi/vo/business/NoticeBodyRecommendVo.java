package com.github.hollykunge.openapi.vo.business;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: zhuqz
 * @date: 2021/3/25 14:46
 * @description: 推荐
 */
public class NoticeBodyRecommendVo extends NoticeBodyVo{
    @ApiModelProperty("链接地址")
    private String url       ;
    @ApiModelProperty("内容标题")
    private String titile    ;
    @ApiModelProperty("DATE yyyy-mm-dd hh:mi:ss")
    private String time      ;
    @ApiModelProperty("内容来源地")
    private String source    ;
    @ApiModelProperty("作者")
    private String author    ;
    @ApiModelProperty("领域")
    private String domain    ;
    @ApiModelProperty("推荐源头")
    private String rsource   ;
    @ApiModelProperty("相关度")
    private Double relevancy ;
    @ApiModelProperty("备注")
    private String bz        ;
}