package com.github.hollykunge.openapi.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhuqz
 * @date: 2021/3/18 16:03
 * @description: 发送通知vo
 */
@ApiModel("通知vo")
@Data
public class NoticeVo  implements Serializable {
    private static final long serialVersionUID = 7834432838358854043L;
    @ApiModelProperty("通知人姓名")
    private String senderName    ;
    @ApiModelProperty("接收人")
    private String receiverId    ;
    @ApiModelProperty("接收人姓名")
    private String receiverName  ;
    @ApiModelProperty("SENDER_ORG_NAME")
    private String senderOrgName;
    @ApiModelProperty("通知来源（业务/应用名称）")
    private String sourceName    ;;
    @ApiModelProperty("801：审批；802：系统消息；803：数据更新；804：推荐")
    private Integer senderType   ;     ;
    @ApiModelProperty("紧急状态（0重要、1非重要）")
    private Integer status        ;;
    @ApiModelProperty("密级 1：非密，2秘密，3机密")
    private Integer noticeLevel  ;
    @ApiModelProperty("备注")
    private String bz            ;
    @ApiModelProperty("内容")
    private Object msgContent      ;
}