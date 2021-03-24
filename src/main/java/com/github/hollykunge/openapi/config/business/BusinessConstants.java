package com.github.hollykunge.openapi.config.business;

/**
 * @author: zhuqz
 * @date: 2021/3/22 16:56
 * @description:
 */
public class BusinessConstants {
    public final static int SUCCESS = 1;
    //审批
    public final static int NOTICE_TYPE_APPROVE = 801;
     //系统消息
    public final static int NOTICE_TYPE_SYS = 802;
     //数据更新
    public final static int NOTICE_TYPE_UPDATE = 803;
     //推荐
    public final static int NOTICE_TYPE_RECOMMEND = 804;

    //未读
    public final static int NOTICE_READ_NO = 0;
    //已读
    public final static int NOTICE_READ_YES = 1;
    //已同意
    public final static int NOTICE_READ_APPROVED = 2;
    //已驳回
    public final static int NOTICE_READ_NEGTIVE = 3;
    //已阅
    public final static int NOTICE_READ_VIEWED = 4;

}