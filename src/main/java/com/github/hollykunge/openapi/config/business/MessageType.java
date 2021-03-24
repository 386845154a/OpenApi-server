package com.github.hollykunge.openapi.config.business;

/**
 *@Description: 消息码
 *@Param:
 *@return:
 *@Author: 忠
 *@date: 2019/5/29
 */
public class MessageType {

    /**
     * 用于系统消息，每个登录用户都要加入系统消息组
     * 系统通知
     */
    public static final String GROUP_SYS = "Iwork-Message";
    public static final String GROUP_SYS_NAME = "云雀全员";
    /**个人消息*/
    public static final int PRIVATE_MSG = 200100;
    //群消息
    public static final int GROUP_MSG = 200200;
    //系统消息
    public static final int SYS_MSG = 2;
    //会议消息
    public static final int MEET_MSG = 200300;

    //群创建
    public static final int GROUP_CREATE = 200204;
    //退群
    public static final int GROUP_QUIT = 200202;
    //群组操作
    public static final int GROUP_EDIT = 200203;
    //群解散
    public static final int GROUP_DISSOLVE = 200205;
    //加入群组
    public static final int GROUP_JOIN_MSG = 5;
    //邀请加入群组
    public static final int GROUP_INVITE_MSG = 6;
    //退出群组
    public static final int GROUP_EXIT_MSG = 7;
    //关闭群组
    public static final int GROUP_CLOSE_MSG = 8;

    //返回码 boolean
    public static final int MSG_EDIT_READ = 9;
    //队列
    //人员、组织机构变更

    //工作台

    //消息应答
    //消息应答码
    public static final int MSG_SEND_ANSWER = 300101;
    //成功
    public static final int SUCCESS_ANSWER = 200;
    //失败
    public static final int FAIL_ANSWER = 201;
    //提示
    public static final int TIPS_ANSWER = 202;
    //不在线
    public static final int OFFLINE_ANSWER = 204;

    //在线状态 7
    public static final int LINESTATUS = 700100;
    //在线
    public static final int ONLINE = 700;
    //离线
    public static final int OFFLINE = 701;

    /*群或者会议以及其他资源流水操作类型码*/
    //新建
    public static final String FLOW_NEW = "300";
    //新增成员
    public static final String FLOW_ADD_MEMBER = "301";
    //删除成员
    public static final String FLOW_DELETE_MEMBER = "302";
    //群解散
    public static final String FLOW_DISSOLUTION= "303";
    //上传群文件
    public static final String FLOW_UPLOADFILE= "304";
    //下载群文件
    public static final String FLOW_DOWNLOADFILE= "305";
    //群创建审批
    public static final String FLOW_APPROVE= "306";
    //文件下载权限分配
    public static final String FLOW_FILE_AUTHORITY= "307";
    //审批人员增加
    public static final String FLOW_APPROVE_ADD_MEMBER= "308";
    //审批人员减少
    public static final String FLOW_APPROVE_DELETE_MEMBER= "309";
    //撤销
    public static final String FLOW_CANCEL = "310";

    /**会议参数*/
    /**创建会议*/
    public static final int CREATE_MEETING = 901;
    /**结束会议*/
    public static final int CLOSE_MEETING = 902;
    /**会议信息变更*/
    public static final int MEET_CHANGE = 200302;
    /**会议删除*/
    public static final int MEET_DELETE = 200304;
    /**会议权限变更*/
    public static final int ROLE_CHANGE= 904;

    /**会议审批通过*/
    public static final int MEETING_APPROVE_PASS = 906;
    /**新增会议*/
    public static final int MEETING_ADD = 200301;
    /**会议剔除人员*/
    public static final int MEETING_QUIT = 200303;
    /**会议进行中*/
    public static final int MEETING_GOING_ON = 908;

    /**会议文件类型*/
    /**参评*/
    public static final int MEETING_FILE_APPROVE = 9000;
    /**参考*/
    public static final int MEETING_FILE_REFERECE = 9001;
    /**结论*/
    public static final int MEETING_FILE_RESULT = 9002;

    /**私人文件*/
    public static final int PRIVATE_FILE = 0;
    /**群文件*/
    public static final int GROUP_FILE = 1;
    /**会议文件*/
    public static final int MEETING_FILE= 905;

    //流水日志群
    public static final  String FLOW_LOG_GROUP = "0";
    //流水日志会议
    public static final  String FLOW_LOG_MEET = "1";
    /**群、会议创建、附件上传下载等等是否需要审批的通知的消息代码*/
    public static final int APPROVE_AUTHORITY_CODE = 500100;
    /**需要审批权限*/
    public static final int REQUIRE_APPROVE_AUTHORITY = 1;
    /**不需要审批权限*/
    public static final int NO_REQUIRE_APPROVE_AUTHORITY = 0;
    /**跨场所类型*/
    /**
     * 院机关编码
     */
    public static final String ORG_CODE_INSTITUTE_ORGAN = "001000";
    /**科室内*/
    public static final String CROSSTYPE_SAME_OFFICE = "0";
    /**跨科室*/
    public static final String CROSSTYPE_DIFF_OFFICE = "1";
    /**跨场所*/
    public static final String CROSSTYPE_DIFF_WORKSPACE = "2";

    /**密级类型*/
    /**非密*/
    public static final String NO_SECRECT_LEVEL = "30";
    /**秘密*/
    public static final String  NORMAL_SECRECT_LEVEL = "40";
    /**机密*/
    public static final String HIGH_SECRECT_LEVEL = "60";

    /**
     * 信息中心 编码规则六位 三层级别 前两位是信息中心大类，如20代表单人消息
     * 后四位对于业务代码，中间两位代表业务一层，最后两位代表具体业务
     * 举例：200201 前两位20代表单人消息 中间两位02代表给单人发的是通知类消息，
     * 最后两位01说明接收人点开了和他聊天的界面,看见了所有消息
     * 目前因为前期编码未考虑，没有完全遵守该规则
     **/
    /**群体消息**/
    public static final String SOCKET_TEAM = "100000";
    /**单人消息**/
    public static final String SOCKET_SINGLE = "200000";
    /**单人通知对方打开消息面包 通知发送人，接收人点开了和他聊天的界面,看见了所有消息**/
    public static final String SOCKET_SINGLE_NOTE_SEEMSG = "200101";
    /**消息撤销、删除**/
    public static final String SOCKET_MSG_DEL = "200102";
    //消息接收应答
    public static final String MSG_RECEIVE_ANSWER = "300102";
    //消息接收确认
    public static final String MSG_RECEIVB_CONFIRM = "300103";
    //离线消息
    public static final String SOCKET_SINGLE_OFFLINE_MSG = "400100";
    //握手反馈
    public static final String SOCKET_SINGLE_HAND_SHAKE = "200401";
    /**群体socket群体绑定**/
    public static final String SOCKET_TEAM_BIND = "300000";
    /**群体列表socket群体绑定用户**/
    public static final String SOCKET_TEAM_BIND_LIST = "400000";
    /**群体socket绑定解除**/
    public static final String SOCKET_TEAM_UNBIND = "500000";
    /**新增收藏**/
    public static final String SOCKET_COLLECT_ADD = "800100";
    /**删除收藏*/
    public static final String SOCKET_COLLECT_DELETE = "800101";
    /**消息传达失败*/
    public static final String SOCKET_MSG_SEND_ERROR = "900100";
    /**信息中心编码，默认**/
    public static final String SOCKET_DEFAULT = "999999";
    /**信息中心详细编码，默认**/
    public static final String SOCKET_DETAIL_DEFAULT = "999999";
    /***在线客服信息id*/
    public static final String ONLINE_MSG_ID = "999999";
    /***系统通知消息*/
    public static final String BACKGROUND_NOTICE_ID = "888888";

    /**创建会议、群组审批 类型**/
    public static final String CREATE_APPROVE_GROUP = "3";
    public static final String CREATE_APPROVE_MEET = "901";

    /**消息体解析**/
    //是否at
    public static final String UN_AT = "0";
    public static final String AT = "1";
    //是否置顶
    public static final String UN_TOP = "0";
    public static final String TOP = "1";
    //群 会议 个人 参数
    public static final String PARAMETER_TYPE_USER = "USER";
    public static final String PARAMETER_TYPE_GROUP = "GROUP";
    public static final String PARAMETER_TYPE_MEET = "MEET";
    public static final String PARAMETER_TYPE_ALL = "ALL";
    public static final String PARAMETER_TYPE_ORG = "ORG";
    //消息体发送人
    public static final String MESSAGE_BODY_SENDER = "fromId";
    //消息体接收人
    public static final String MESSAGE_BODY_RECEIVER = "toId";
    //消息体发送人名称
    public static final String MESSAGE_BODY_SENDER_NAME = "username";

    /**mq可靠性---6**/
    /**
     * 发送中
     */
    public static final int NEWS_SENDING = 600001;
    /**
     * 发送到交换机
     */
    public static final int NEWS_BORKDER_SUCCESS = 600002;
    /**
     * 发送到交换机失败
     */
    public static final int NEWS_BORKDER_FAILURE = 600003;
    /**
     * 重试3次依然发送失败
     */
    public static final int NEWS_SEND_FAILURE = 600004;
    /**
     * 消费中
     */
    public static final int NEWS_CONSUMER = 600101;
    /**
     * 消费成功
     */
    public static final int NEWS_CONSUMER_SUCCESS = 600102;
    /**
     * 消费失败
     */
    public static final int NEWS_CONSUMER_FAILURE = 600103;
    /**
     * 消息可靠性确认失败
     */
    public static final int NEWS_CONFRIM_FAILURE = 600104;
    public static final int ORDER_TIMEOUT = 1; /*分钟超时单位：min*/
    public static final String SYSTEM_SENDER = "system";
    /**
     * 后台发送消息，发送人
     */
    public static final String BACK_GROUND_SENDER = "background";
    /**
     * 后台发送消息，发送人姓名
     */
    public static final String BACK_GROUND_SENDER_NAME = "云雀助手";
    /**
     * 消息类型 通知
     */
    public static final int MESSAGE_CONTENT_TYPE_NOTICE = 8888;
    /**
     * 消息类型 文本
     */
    public static final int MESSAGE_CONTENT_TYPE_TEXT = 1;
    /**
     * 消息类型 图片
     */
    public static final int MESSAGE_CONTENT_TYPE_PIC = 2;
    /**
     * 消息类型 附件
     */
    public static final int MESSAGE_CONTENT_TYPE_FILE = 3;
    /**
     * 消息类型 语音
     */
    public static final int MESSAGE_CONTENT_TYPE_VOICE = 4;
    /**
     * 消息类型 会议变动
     */
    public static final int MESSAGE_CONTENT_TYPE_MEET_CHANGE = 999;
    /**
     * 通知类型 文本
     */
    public static final String NOTICE_TYPE_TEXT = "0";
    /**
     * 通知类型 超链接
     */
    public static final String NOTICE_TYPE_SUPER_TEXT = "1";
    /**
     * 通知类型 选择
     */
    public static final String NOTICE_TYPE_CHOICE = "2";
    /**
     * 通知状态 文本需处理
     */
    public static final String NOTICE_STATUS_TEXT = "0";
    /**
     * 通知状态 超级连接
     */
    public static final String NOTICE_STATUS_SUPER_TEXT = "1";
    /**
     * 通知类型 同意不同意 未处理
     */
    public static final String NOTICE_STATUS_CHOICE_WAIT = "2";
    /**
     * 通知类型 同意不同意 同意
     */
    public static final String NOTICE_STATUS_CHOICE_YES = "3";
    /**
     * 通知类型 同意不同意 不同意
     */
    public static final String NOTICE_STATUS_CHOICE_NO = "4";
    /**
     * 通知来源 研讨
     */
    public static final String NOTICE_SOURCE = "云雀研讨";
    /**
     * 通知来源 研讨
     */
    public static final String NOTICE_SOURCE_GROUP = "群组";
    /**
     * 通知来源 研讨
     */
    public static final String NOTICE_SOURCE_MEET = "会议";
    /**
     * 院群体通知默认前缀
     */
    public static final String NOTICE_ORG_PREFIX = "org-";
    public static final String NOTICE_ORG_NAME = "院全员";

    /**来自openapi通知*/
    public static final String NOTICE_OPENAPI = "100100";

}
