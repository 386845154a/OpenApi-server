package com.github.hollykunge.openapi.config.business;

/**
 * @author: zhhongyu
 * @description: 交换机常量类
 * @since: Create in 10:45 2020/3/30
 */
public class RabbiMqExchangeConstant {
    /** 公告通知交换机名称*/
    public static final String NOTICE_EXCHANGE = "noticeExchange";
    /**
     * 通知死信队列交换机名称
     */
    public final static String NOTICE_DEAD_EXCHANGENAME = "notice_dead_exchange";
    /**
     * 未消费掉的人员或组织使用的交换机
     */
    public static final String ADMIN_USERORORG_EXCHANGE = "adminUserAndOrgExchange";
    /**
     * ADMIN组织用户交换机名称
     */
    public final static String WERSERVICE_ADMIN_USERANDORG_EXCHANGE = "webAdminUserAndOrgExchange";
    public static final String EXCHANGE_CONTACT = "exchange_contact";
    /**
     * 协同编辑未被消费的人员组织交换机
     */
    public final static String ONE_DOC_EXCHANGE = "onedoc_exchange";

    /**
     * service-task 服务
     * 邀请成员交换机
     */
    public static final String INVITEMEMBER_EXCHANGE = "task_inviteMember_EXCHANGE";
    /**
     * service-task 服务
     * 邀请成员死信交换机
     */
    public final static String INVITEMEMBER_DEAD_EXCHANGENAME = "task_inviteMember_dead_exchange";

    /**
     * service-chat 服务
     */
    public static final String EXCHANGE_SYSMSG = "exchange_sysmsg";

    /**
     * service-chat 服务
     * socket消息队列
     */
    public static final String EXCHANGE_SOCKET = "exchange_socket";
}
