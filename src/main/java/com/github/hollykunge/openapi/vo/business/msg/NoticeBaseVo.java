package com.github.hollykunge.openapi.vo.business.msg;

import lombok.Data;

/**
 * @author: zhuqz
 * @date: 2020/11/27 14:14
 * @description:
 */
@Data
public class NoticeBaseVo extends MsgBaseVo{
    /*
    通知详细
     */
    private String msgDetail;
    /*
    普通文本0； 超链接1； 是否同意未操作2；  是否同意点击了同意3；是否同意点击了不同意4
     */
    private String status = "0";
}