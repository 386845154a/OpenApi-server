package com.github.hollykunge.openapi.config.business;

/**
 * @author: zhhongyu
 * @description: rabbitmq队列常量类
 * @since: Create in 10:44 2020/3/30
 */
public class RabbitMqQueConstant {
    /**
     * 通知死信队列名称
     */
    public final static String NOTICE_DEAD_QUEUENAME = "notic_dead_queue";
    /**
     * 公告推送到研讨服务队列名称
     */
    public static final String NOTICE_TOWECHAT_QUEUE_NAMA = "noticeToChatService";
    /** 公告队列名称 */
    public static final String NOTICE_QUEUE_NAMA = "noticeQueue";
    /**
     * admin服务中人员未被消费队列
     */
    public static final String ADMIN_UNACK_USER = "admin_unack_user_queue";
    /**
     * admin服务中组织未被的队列
     */
    public static final String ADMIN_UNACK_ORG = "admin_unack_org_queue";
    /** 取消公告分布*/
    public static final String CANCEL_NOTICE_QUEUE_NAME = "cancelNoticeQueue";

    /**
     * 用户队列名称
     */
    public final static String ADMINUSER_QUEUE_NAME = "adminUserQueue";
    /**
     * 组织队列名称
     */
    public final static String ADMINORG_QUEUE_NAME = "adminOrgQueue";

    /**
     * 推送到协同编辑用户队列名称
     */
    public final static String ONEDOCUSER_QUEUE_NAME = "oneDocUserQueue";
    /**
     * 推送到协同编辑组织队列名称
     */
    public final static String ONEDOCORG_QUEUE_NAME = "oneDocOrgQueue";
    /**
     * 协同编辑返回用户队列名称
     */
    public final static String ONEDOC_USER = "onedoc_user";
    /**
     * 协同编辑返回组织队列名称
     */
    public final static String ONEDOC_ORG = "onedoc_org";

    public static final String QUEUE_CONTACT = "queue_contact";

    /**
     * service-task 服务
     * 邀请成员队列
     */
    public static final String INVITEMEMBER_QUEUE = "task_inviteMember_queue";

    /**
     * service-task 服务
     * 邀请成员死信队列
     */
    public final static String INVITEMEMBER_DEAD_QUEUE = "task_inviteMember_dead_queue";

    /**
     * service-chat 服务
     */
    public static final String QUEUE_SYSMSG = "queue_sysmsg";

    /**
     * service-chat 服务
     * 群(会议)变更消息队列
     */
    public static final String QUEUE_GROUPCHANGE = "queue_groupchange";

    /**
     * service-chat 服务
     * 群(会议)审批日志队列
     */
    public static final String QUEUE_GROUP_APPROVELOG = "queue_group_approvelog";

    /**
     * service-chat 服务
     * 群体消息（群，会议，系统通知等等）
     */
    public static final String QUEUE_SOCKET_TEAM_MSG  = "queue_socket_team_msg";

    /**
     * service-chat 服务
     * 私人消息
     */
    public static final String QUEUE_SOCKET_PRIVATE_MSG  = "queue_socket_private_msg";

    /**
     * service-chat 服务
     * 群体消息绑定（把单个人的id绑定到某些群体，如群、会议等等）
     */
    public static final String QUEUE_SOCKET_TEAMLIST_BIND  = "queue_socket_teamlist_bind";

    /**
     * service-chat 服务
     */
    public static final String QUEUE_SOCKET_TEAM_BIND  = "queue_socket_team_bind";

    /**
     * service-chat 服务
     * 群体消息解除绑定（把单个人的id绑定到某个群体，如群、会议等等）
     */
    public static final String QUEUE_SOCKET_TEAM_UNBIND  = "queue_socket_team_unbind";
}
