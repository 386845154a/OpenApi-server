package com.github.hollykunge.openapi.vo.res;


import com.github.hollykunge.openapi.vo.base.ResVo;
import lombok.Data;

/**
 * @author: zhuqz
 * @date: 2021/3/5 11:08
 * @description:
 */
@Data
public class LoginSuccessTokenVo extends ResVo {
    private String token;
    private String refreshToken;

}