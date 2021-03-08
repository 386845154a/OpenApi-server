package com.github.hollykunge.openapi.vo.res;

import com.github.hollykunge.openapi.vo.base.ResVo;
import lombok.Data;

/**
 * @author: zhuqz
 * @date: 2021/3/5 16:06
 * @description:
 */
@Data
public class RefreshUesrTokenVo  extends ResVo {
    String token;
}