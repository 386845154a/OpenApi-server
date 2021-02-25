package com.github.hollykunge.openapi.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/28 15:42
 * @description: token
 */
@Data
public class ApiToken implements Serializable {
    private static final long serialVersionUID = -6239791467803484403L;
    private long crtTime;
    private long expireTime;
    private String appId;
    private String accessToken;
    private int status = 1;
}