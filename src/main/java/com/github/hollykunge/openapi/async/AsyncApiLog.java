package com.github.hollykunge.openapi.async;

import com.github.hollykunge.openapi.biz.ApiLogBiz;
import com.github.hollykunge.openapi.config.CommonUtil;
import com.github.hollykunge.openapi.config.UUIDUtils;
import com.github.hollykunge.openapi.entity.ApiLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: zhuqz
 * @date: 2021/4/1 14:22
 * @description: 异步日志
 */
@Service
public class AsyncApiLog {
    private Logger logger = LoggerFactory.getLogger(AsyncApiLog.class);
    @Autowired
    ApiLogBiz apiLogBiz;
    @Async("asyncServiceExecutor")
    public  void writeApiLog(ApiLog apiLog){
        try {
            apiLog.setId(UUIDUtils.generateShortUuid());
            if(CommonUtil.no2EmptyStr(apiLog.getReturnObj()).length()>1333){
                apiLog.setReturnObj(apiLog.getReturnObj().substring(0,1333));
            }
            if(CommonUtil.no2EmptyStr(apiLog.getParam()).length()>1333){
                apiLog.setParam(apiLog.getParam().substring(0,1333));
            }
            if(CommonUtil.no2EmptyStr(apiLog.getBody()).length()>1333){
                apiLog.setBody(apiLog.getBody().substring(0,1333));
            }
            apiLogBiz.justInsertSelective(apiLog);
        } catch (Exception e) {
            logger.error("异步日志错误");
            logger.error(CommonUtil.getExceptionMessage(e));
        }
    }
}