package com.github.hollykunge.openapi.vo.business.msg;


import com.github.hollykunge.openapi.vo.business.msg.children.ContactInfoVo;
import com.github.hollykunge.openapi.vo.business.msg.children.ContentVo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuqz
 * @date: 2020/9/23 15:49
 * @description: 前端发送消息的基础结构
 */
@Data
public class MsgBaseVo implements Serializable {
    private static final long serialVersionUID = -1335138245610729692L;
    /**
     * 联系人
     */
    private ContentVo content;
    /**
     * 联系人信息 如果是私聊 发送人； 如果是群或者会议消息 就是群或者会议信息
     */
    private ContactInfoVo contactInfo;
    private String id;
    /**
     * 发送人姓名
     */
    private String username;
    /**
     * 发送人头像
     */
    private String avatar;
    private String fromId;
    private String toId;
    private String toName;
    private List<Object> atId = new ArrayList<>();
    private String time;
    /**
     * 群和会议true
     */
    private Boolean isGroup = false;
}