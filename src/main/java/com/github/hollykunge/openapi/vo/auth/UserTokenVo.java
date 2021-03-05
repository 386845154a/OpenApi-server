package com.github.hollykunge.openapi.vo.auth;

import com.github.hollykunge.openapi.vo.base.ResVo;
import lombok.Data;

/**
 * @author: zhuqz
 * @date: 2021/3/5 14:18
 * @description:
 */
@Data
public class UserTokenVo extends ResVo {
    private String token;
    private String refreshToken;
}