package com.github.hollykunge.openapi.controller.rest;

import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.interceptor.RequestThreadLocal;
import com.github.hollykunge.openapi.service.ApiService;
import com.github.hollykunge.openapi.vo.param.OpenApiRequestParamVo;
import com.github.hollykunge.openapi.vo.res.OpenApiResVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author: zhuqz
 * @date: 2020/6/22 15:22
 * @description: api接口调用
 */
@RestController
@RequestMapping("/openApi")
public class ApiController {
    @Autowired
    ApiService apiService;
    @Autowired
    HttpServletRequest request;
    @PostMapping("/service")
    public OpenApiResVo requestApi(@RequestBody OpenApiRequestParamVo param){
        String appId = RequestThreadLocal.getApp();
        if(param.getRequestParams()==null){
            param.setRequestParams(new HashMap<>(4));
        }
       return apiService.requestApi(appId,param);
    }
}