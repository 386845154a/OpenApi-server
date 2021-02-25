package com.github.hollykunge.openapi.vo.param;

import lombok.Data;

import java.util.Map;

/**
 * @author: zhuqz
 * @date: 2020/6/22 15:26
 * @description: 调用openapi参数
 */
@Data
public class OpenApiRequestParamVo {
    /**
     * api接口的id
     */
    private String serviceId;
    /**
     * request 参数
     */
    private Map<String,String> requestParams;
    /**
     * body参数(json)
     */
    private Object requestBody;
}