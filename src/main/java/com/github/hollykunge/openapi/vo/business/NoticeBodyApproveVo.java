package com.github.hollykunge.openapi.vo.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zhuqz
 * @date: 2021/3/22 17:07
 * @description:
 */
@Data
public class NoticeBodyApproveVo extends NoticeBodyVo{
    @ApiModelProperty("业务单号")
    private String businessId  ;
    @ApiModelProperty("业务详情")
    private String detailUrl  ;
    @ApiModelProperty("类型")
    private String type        ;
    @ApiModelProperty("事件描述（摘要）")
    private String contentAbs ;
    @ApiModelProperty("同意、驳回接口，回调地址，参数固定")
    private String callBack   ;
    @ApiModelProperty("理由回填（同意、驳回）")
    private String msg         ;
    @ApiModelProperty("图片地址")
    private String imgUrl     ;
    @ApiModelProperty("备注")
    private String bz          ;
}