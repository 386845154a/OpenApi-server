package com.github.hollykunge.openapi.config.business;

/*
信息中心 消息详细类型枚举：群消息、会议变更消息、私聊等等
 */
public enum SocketMsgDetailTypeEnum {
    //消息类型对照表 必须保证消息的类型是唯一的
    //私聊200100
    PRIVATE_MSG(MessageType.PRIVATE_MSG+""),
    //消息发送应答300101
    PRIVATE_SEND_ANSWER(MessageType.MSG_SEND_ANSWER+""),
    //对方打开了聊天的消息面板200101
    PRIVATE_RECEIVER_OPEN_BOARD(MessageType.SOCKET_SINGLE_NOTE_SEEMSG),
    //对方已读(这个目前是客户端给服务器发送，后期可以作废)
    PRIVATE_RECEIVER_READ(MessageType.MSG_EDIT_READ+""),
    //接收应答300102
    PRIVATE_RECEIVER_ANSWER(MessageType.MSG_RECEIVE_ANSWER),
    //接收确认300103
    PRIVATE_RECEIVER_CONFIRM(MessageType.MSG_RECEIVB_CONFIRM),
    //离线消息400100
    PRIVATE_OFF_LINE_MSG(MessageType.SOCKET_SINGLE_OFFLINE_MSG),
    //握手反馈消息200401
    PRIVATE_HANDSHAKE_MSG(MessageType.SOCKET_SINGLE_HAND_SHAKE),

    //群聊200200
    GROUP_MSG(MessageType.GROUP_MSG+""),
    //群创建200204
    GROUP_CREATE(MessageType.GROUP_CREATE+""),
    //退群200202
    GROUP_QUIT(MessageType.GROUP_QUIT+""),
    //群编辑200203
    GROUP_EDIT(MessageType.GROUP_EDIT+""),
    //群解散200205
    GROUP_DISSOVLE(MessageType.GROUP_DISSOLVE+""),

    //会议200300
    MEET_MSG(MessageType.MEET_MSG+""),
    //会议变更200302
    MEET_CHANGE(MessageType.MEET_CHANGE+""),
    //会议添加(审批通过)200301
    MEETING_ADD(MessageType.MEETING_ADD+""),
    //会议剔除人员200303
    MEETING_QUIT(MessageType.MEETING_QUIT+""),
    //会议删除200304
    MEETING_DELETE(MessageType.MEET_DELETE+""),
    //人员上线或者离线700100
    USER_LINE(MessageType.LINESTATUS+""),

    //特殊权限500100
    AUTHORITY(MessageType.APPROVE_AUTHORITY_CODE+""),

    //消息删除、撤销200102
    MSG_DELTET(MessageType.SOCKET_MSG_DEL),

    //新增收藏800100
    COLLECT_ADD(MessageType.SOCKET_COLLECT_ADD),
    //删除收藏800101
    COLLECT_DELTET(MessageType.SOCKET_COLLECT_DELETE),
    //消息传达失败（消息可靠性）900100
    MSG_SEND_ERROR(MessageType.SOCKET_MSG_SEND_ERROR),
    //openapi 通知100100
    OPEN_API_NOTICE(MessageType.NOTICE_OPENAPI),
    //无需给前端编码时候的默认值,如绑定、解绑等等999999
    DEFAULT(MessageType.SOCKET_DETAIL_DEFAULT+"");

    //编码
    private final String code;

    SocketMsgDetailTypeEnum(String code){
        this.code = code;
    }

    public String getCode(){
        return  this.code;
    };

}
