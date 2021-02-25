package com.github.hollykunge.openapi.vo.res;

import com.github.hollykunge.openapi.vo.base.ResVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/22 15:31
 * @description: 调用openapi返回结果
 */
@Data
public class OpenApiResVo extends ResVo implements Serializable {

    private static final long serialVersionUID = 1096112249414860042L;
    /**
     * 结果
     */
    private Object res;
}