package com.github.hollykunge.openapi.vo.business.msg.children;

import com.github.hollykunge.openapi.config.business.MessageType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/9/23 15:50
 * @description: 前端发送消息，内容结构
 */
@Data
public class ContentVo implements Serializable {
    private static final long serialVersionUID = 8109589447179383121L;
    /**
     * 附件id，如果0，表示文本消息；2/3表示附件id；
     */
    private String id = "0";
    /**
     * 文件大小，不是附件0
     */
    private String fileSize = "0";
    /**
     * 消息类型 1 文字消息; 2图片; 3附件; 999 会议操作提示 ;8888 通知
      */
    private Integer type = 1;
    /**
     * 附件扩展 0 非附件
     */
    private String extension  = "0";
    /**
     * 消息内容，如果是附件，附件的名称
     */
    private String title;
    /**
     * 消息密级
     */
    private Integer secretLevel = Integer.parseInt(MessageType.NO_SECRECT_LEVEL);

}