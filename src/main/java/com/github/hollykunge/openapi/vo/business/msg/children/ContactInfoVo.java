package com.github.hollykunge.openapi.vo.business.msg.children;

import com.github.hollykunge.openapi.config.business.MessageType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/9/23 15:51
 * @description: 前端发送消息联系人结构
 */
@Data
public class ContactInfoVo implements Serializable {
    private static final long serialVersionUID = -7304567208506647607L;
    private String id;
    private String name;
    private String avatar;
    private Integer secretLevel = Integer.parseInt(MessageType.NO_SECRECT_LEVEL);
    private Integer memberNum = 2;
    /**
     * 如果是群群主id
     */
    private String groupOwnerId = "";
    private Boolean isGroup = false;
}