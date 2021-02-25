package com.github.hollykunge.openapi.vo.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/28 15:56
 * @description: 获取token参数
 */
@Data
public class TokenParamVo implements Serializable {
    private static final long serialVersionUID = 7312965690225932407L;
    private String appId;
    private String appSecret;
}