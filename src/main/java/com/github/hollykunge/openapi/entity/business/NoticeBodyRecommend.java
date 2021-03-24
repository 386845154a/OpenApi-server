package com.github.hollykunge.openapi.entity.business;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: zhuqz
 * @date: 2021/3/22 15:53
 * @description: 通知推荐
 */
@Data
@ApiModel("通知消息体推荐")
@Table(name = "BUSINESS_NOTICE_BODY_RECOMMEND")
public class NoticeBodyRecommend  extends BaseEntity {
    @Column(name = "URL")
    @ApiModelProperty("链接地址")
    private String url       ;
    @Column(name = "TITILE")
    @ApiModelProperty("内容标题")
    private String titile    ;
    @Column(name = "TIME")
    @ApiModelProperty("DATE")
    private Date time      ;
    @Column(name = "SOURCE")
    @ApiModelProperty("内容来源地")
    private String source    ;
    @Column(name = "AUTHOR")
    @ApiModelProperty("作者")
    private String author    ;
    @Column(name = "DOMAIN")
    @ApiModelProperty("领域")
    private String domain    ;
    @Column(name = "RSOURCE")
    @ApiModelProperty("推荐源头")
    private String rsource   ;
    @Column(name = "RELEVANCY")
    @ApiModelProperty("相关度")
    private Double relevancy ;
    @Column(name = "BZ")
    @ApiModelProperty("备注")
    private String bz        ;

}