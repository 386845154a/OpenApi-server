package com.github.hollykunge.openapi.vo.business.send;

import com.github.hollykunge.openapi.config.CommonUtil;
import com.github.hollykunge.openapi.config.business.BusinessConstants;
import com.github.hollykunge.openapi.vo.business.NoticeVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zhuqz
 * @date: 2021/3/23 14:23
 * @description:
 */
@Data
public class NoticeSendHeaderVo extends NoticeVo {
    @ApiModelProperty("知主键（研讨内部唯一id）")
    private String noticeId;
    @ApiModelProperty("是否已读 （默认0未读，1已读，2已同意，3已驳回，4已阅）")
    private Integer noticeRead = BusinessConstants.NOTICE_READ_NO;
    @ApiModelProperty("来源IP")
    private String sourceIP;
    @ApiModelProperty("发送时间 yyyy-mm-dd hh:mi:ss")
    private String time = CommonUtil.getServerTime();
    @ApiModelProperty("回执（研讨内部使用，标注消息已阅，取消强提醒）")
    private Integer receipt;

}