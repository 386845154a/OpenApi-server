package com.github.hollykunge.openapi.vo.auth;

import com.github.hollykunge.openapi.vo.base.ResVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/28 13:54
 * @description:
 */
@Data
public class RegisterResVo extends ResVo implements Serializable {

    private static final long serialVersionUID = -7363080496020901208L;
    /**
     * 注册成功返回，应用id
     */
    private String appId;
    /**
     * 注册成功返回，应用秘钥
     */
    private String appSecret;
}