package com.github.hollykunge.openapi.vo.auth;

import com.github.hollykunge.openapi.vo.base.ResVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/28 15:59
 * @description:
 */
@Data
public class TokenResVo extends ResVo implements Serializable {
    private String token;
}