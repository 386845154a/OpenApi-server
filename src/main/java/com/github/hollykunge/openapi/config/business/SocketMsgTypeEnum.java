package com.github.hollykunge.openapi.config.business;

import static com.github.hollykunge.openapi.config.business.MessageType.*;

/**
 * @auther: zhuqz
 * @date: 2020/3/17 15:00
 * @description: 信息中心消息枚举类型(单人、群体、绑定群体、绑定群体列表、解绑)
 */
public enum SocketMsgTypeEnum {
    //消息类型对照表 必须保证消息的类型是唯一的

    //单人
    SINGLE_MSG(SOCKET_SINGLE),
    //群体消息
    TEAM_MSG(SOCKET_TEAM),
    //单个群体绑定用户
    BIND_USER(SOCKET_TEAM_BIND),
    //单个用户绑定多个群体
    BIND_LIST(SOCKET_TEAM_BIND_LIST),
    //单个群体解绑用户
    UNBIND_USER(SOCKET_TEAM_UNBIND),
    //默认编码 这个给信息中心自己处理用的，比如缓存删除等等
    DEFAULT(SOCKET_DEFAULT);
    
    //编码
    private final String code;

    SocketMsgTypeEnum(String code){
        this.code = code;
    }

    public String getCode(){
        return  this.code;
    };

}