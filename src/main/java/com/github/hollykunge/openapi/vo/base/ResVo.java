package com.github.hollykunge.openapi.vo.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/22 09:12
 * @description: 基础功能操作结果（如注册app、接口审批、注册服务等等）
 */
@Data
public class ResVo implements Serializable {
    private static final long serialVersionUID = -1021289967205770057L;
    /**
     * 编码（参考ConfigContants.java）
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
}