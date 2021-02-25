package com.github.hollykunge.openapi.service;

import com.github.hollykunge.openapi.vo.param.OpenApiRequestParamVo;
import com.github.hollykunge.openapi.vo.res.OpenApiResVo;

public interface ApiService {
    /**
     * 调用接口
     * @param appId
     * @param paramVo
     * @return
     */
    OpenApiResVo requestApi(String appId, OpenApiRequestParamVo paramVo);
}
