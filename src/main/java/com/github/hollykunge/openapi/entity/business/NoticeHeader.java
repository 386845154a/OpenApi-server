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
 * @date: 2021/3/18 15:31
 * @description: 通知协议头
 */
@Data
@ApiModel("通知协议头")
@Table(name = "BUSINESS_NOTICE_HEADER")
public class NoticeHeader extends BaseEntity {
    @Column(name = "NOTICE_READ")
    @ApiModelProperty("是否已读 （默认0未读，1已读，2已同意，3已驳回，4已阅）")
    private Integer noticeRead   ;
    @Column(name = "SENDER_NAME")
    @ApiModelProperty("通知人姓名")
    private String senderName    ;
    @Column(name = "RECEIVER_ID")
    @ApiModelProperty("接收人")
    private String receiverId    ;
    @Column(name = "RECEIVER_NAME")
    @ApiModelProperty("接收人姓名")
    private String receiverName  ;
    @Column(name = "SENDER_ORG_NAME")
    @ApiModelProperty("SENDER_ORG_NAME")
    private String senderOrgName;
    @Column(name = "SOURCE_NAME")
    @ApiModelProperty("通知来源（业务/应用名称）")
    private String sourceName    ;
    @Column(name = "SOURCE_IP")
    @ApiModelProperty("来源IP")
    private String sourceIp      ;
    @Column(name = "SENDER_TYPE")
    @ApiModelProperty("801：审批；802：系统消息；803：数据更新；804：推荐")
    private Integer senderType   ;
    @Column(name = "TIME")
    @ApiModelProperty("通知发送时间")
    private Date time          ;
    @Column(name = "STATUS")
    @ApiModelProperty("紧急状态（0重要、1非重要）")
    private Integer status        ;
    @Column(name = "RECEIPT")
    @ApiModelProperty("回执（研讨内部使用，标注消息已阅，取消强提醒）")
    private Integer receipt       ;
    @Column(name = "NOTICE_LEVEL")
    @ApiModelProperty("密级 1：非密，2秘密，3机密")
    private Integer noticeLevel  ;
    @Column(name = "BZ")
    @ApiModelProperty("备注")
    private String bz            ;
    @Column(name = "DETAIL_ID")
    @ApiModelProperty("详细ID")
    private String detailId      ;
}