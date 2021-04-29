package com.github.hollykunge.openapi.vo.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author: zhuqz
 * @date: 2021/3/25 14:38
 * @description: 数据更新通知vo
 */
@Data
public class NoticeBodyUpdateVo extends NoticeBodyVo{
    @ApiModelProperty("项目名称")
    private String projectName ;
    @ApiModelProperty("项目id（回调查看项目相关信息使用）")
    private String projectId   ;
    @ApiModelProperty("原始数据")
    private String postData    ;
    @ApiModelProperty("变更后数据")
    private String changeData  ;
    @ApiModelProperty("数据版本（最新）")
    private String version      ;
    @ApiModelProperty("关联性（强1、弱0关联）")
    private String correlation  ;
    @ApiModelProperty("备注")
    private String bz           ;
}