package com.github.hollykunge.openapi.controller.rest;

import com.github.hollykunge.openapi.async.AsyncApiLog;
import com.github.hollykunge.openapi.config.CommonUtil;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.entity.ApiLog;
import com.github.hollykunge.openapi.interceptor.RequestThreadLocal;
import com.github.hollykunge.openapi.service.ApiService;
import com.github.hollykunge.openapi.service.impl.ApiServiceImpl;
import com.github.hollykunge.openapi.vo.param.OpenApiRequestParamVo;
import com.github.hollykunge.openapi.vo.res.OpenApiResVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    ApiService apiService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    AsyncApiLog asyncApiLog;
    @PostMapping("/service")
    public OpenApiResVo requestApi(@RequestBody OpenApiRequestParamVo param){

        ApiLog apiLog = new ApiLog();
        try {
            String appId = RequestThreadLocal.getApp();
            if(param.getRequestParams()==null){
                param.setRequestParams(new HashMap<>(4));
            }
            apiLog.setAppId(CommonUtil.no2EmptyStr(appId));
            apiLog.setServiceId(CommonUtil.no2EmptyStr(param.getServiceId()));
            apiLog.setParam(CommonUtil.no2EmptyStr(param.getRequestParams()).toString());
            apiLog.setBody(CommonUtil.no2EmptyStr(param.getRequestBody()).toString());
            OpenApiResVo openApiResVo = apiService.requestApi(appId, param);
            if(!ConfigConstants.RES_SUCCESS.equals(CommonUtil.no2EmptyStr(openApiResVo.getCode()))){
                apiLog.setRes(ConfigConstants.RES_ERROR_SYSTEM);
                apiLog.setMsg(CommonUtil.no2EmptyStr(openApiResVo.getMsg()));
            }
            try {
                apiLog.setReturnObj(CommonUtil.no2EmptyStr(openApiResVo.getRes()));
            } catch (Exception e) {
                logger.error("api 接口日志出错！");
                logger.error(CommonUtil.getExceptionMessage(e));
                apiLog.setReturnObj("");
            }
            return openApiResVo;
        } catch (Exception e) {
            logger.error("api 接口调用出错！");
            logger.error(CommonUtil.getExceptionMessage(e));
            apiLog.setRes(ConfigConstants.RES_ERROR_SYSTEM);
            apiLog.setMsg(CommonUtil.no2EmptyStr(CommonUtil.getExceptionMessage(e)));
           throw e;
        }finally {
            //记录日志异步
            asyncApiLog.writeApiLog(apiLog);
        }

    }
}