package com.github.hollykunge.openapi.config.business;

/**
 * @author: zhhongyu
 * @description: 路由键常量类
 * @since: Create in 11:14 2020/3/30
 */
public class RabbitMqRoutingKeyConstant {
    /**
     * 死信队列 交换机标识符
     */
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    /**
     * 通知死信队列路由键
     */
    public final static String NOTICE_DEAD_ROUTING_KEY = "notice_dead_routing_key";
    /**
     * 通知公告发送到门户服务路由键
     */
    public static final String NOTICE_TOPORTAL_ROTEING_KEY = "notic_to_portal";
    /**
     * 通知公告发送到研讨服务路由键
     */
    public static final String NOTICE_TOCHAT_ROTEING_KEY = "notic_to_chat";
    /**
     * 路由键
     */
    public static final String ADMIN_UNACK_USER_KEY = "admin_unack_user_key";
    /**
     * 路由键
     */
    public static final String ADMIN_UNACK_ORG_KEY = "admin_unack_org_key";
    /**
     * fansq 发送取消公告到portal服务路由键
     */
    public static final String CANCEL_NOTICE_TOPORTAL_ROTEING_KEY = "cancel_notic_to_portal";
    /**
     * 用户路由键
     */
    public static final String ADMINUSER_ROTEING_KEY = "admin_user";
    /**
     * 组织路由键
     */
    public static final String ADMINORG_ROTEING_KEY = "admin_org";

    public static final String ROUTINGKEY_CONTACT = "routingkey_contact";

    public static final String ROUTINGKEY_ONE_DOC_USERUNACK = "onedoc_user_key";

    public static final String ROUTINGKEY_ONE_DOC_ORGUNACK = "onedoc_org_key";

    /**
     * service-task 服务
     * 死信队列 交换机标识符
     */
    public static final String DEAD_LETTER_QUEUE_KEY_TASK = "x-dead-letter-exchange";
    /**
     * service-task 服务
     * 死信队列交换机绑定键标识符
     */
    public static final String DEAD_LETTER_ROUTING_KEY_TASK = "x-dead-letter-routing-key";
    /**
     * service-task 服务
     * 通知死信队列路由键
     */
    public final static String INVITEMEMBER_DEAD_ROUTING_KEY = "task_inviteMember_dead_routing_key";

    /**
     * service-task 服务
     * 邀请成员路由键
     */
    public static final String INVITEMEMBER_ROUTING_KEY = "task_inviteMember_routing_key";

    /**
     * service-chat 服务
     */
    public static final String ROUTINGKEY_SYSMSG = "routingKey_sysmsg";

    /**
     * service-chat 服务
     *群(会议)变更消息队列路由
     */
    public static final String ROUTINGKEY_GROUPCHANGE = "routingkey_groupchange";

    /**
     * service-chat 服务
     * 群(会议)审批日志消息队列路由
     */
    public static final String ROUTINGKEY_GROUPAPPROVELOG = "routingkey_group_approvelog";

    /**
     * service-chat 服务
     * 群(会议)审批日志消息队列路由
     */
    public static final String ROUTINGKEY_SOCKET_TEAM_MSG = "routingkey_socket_team_msg";

    /**
     * service-chat 服务
     * 私人消息
     */
    public static final String ROUTINGKEY_SOCKET_PRIVATE_MSG = "routingkey_socket_private_msg";

    /**
     * service-chat 服务
     * 群体消息绑定（把单个人的id绑定到某些群体，如群、会议等等）
     */
    public static final String ROUTINGKEY_SOCKET_TEAMLIST_BIND = "routingkey_socket_teamlist_bind";

    /**
     * service-chat 服务
     */
    public static final String ROUTINGKEY_SOCKET_TEAM_BIND = "routingkey_socket_team_bind";

    /**
     * service-chat 服务
     * 群体消息解除绑定（把单个人的id绑定到某个群体，如群、会议等等）
     */
    public static final String ROUTINGKEY_SOCKET_TEAM_UNBIND = "routingkey_socket_team_unbind";
}
