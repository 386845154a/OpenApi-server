package com.github.hollykunge.openapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.hollykunge.openapi.biz.AppBiz;
import com.github.hollykunge.openapi.biz.ApplyBiz;
import com.github.hollykunge.openapi.biz.ServiceBiz;
import com.github.hollykunge.openapi.biz.TokenBiz;
import com.github.hollykunge.openapi.config.CommonUtil;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.entity.ApiLog;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.entity.Apply;
import com.github.hollykunge.openapi.service.ApiService;
import com.github.hollykunge.openapi.vo.param.OpenApiRequestParamVo;
import com.github.hollykunge.openapi.vo.res.OpenApiResVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuqz
 * @date: 2020/6/22 15:24
 * @description: 调用服务
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService {
    private Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);
    @Autowired
    ServiceBiz serviceBiz;
    @Autowired
    AppBiz appBiz;
    @Autowired
    ApplyBiz applyBiz;
    @Autowired
    TokenBiz tokenBiz;

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @Value("${selfAppId}")
    private String selfAppId;
     //自身app的token
    private  volatile static String appSelfToken;
    //自身包含的服务列表
    private  volatile static List<String> appSelfServiceIds;

    public  static void addSelfServiceId(String serviceId){
        appSelfServiceIds.add(serviceId);
    }

    @PostConstruct
    public void initSelfApp(){
        appSelfToken = tokenBiz.getSelfToken(selfAppId);
        if(CommonUtil.no2EmptyStr(appSelfToken).equals("")){
            logger.warn("api 工程初始化错误，没有自身app数据，请注册自身为一个app服务,并申请token，或者导入入初始化脚本！");
            return;
        }
        appSelfServiceIds = this.serviceBiz.getSelfAppServices(selfAppId);

        if(appSelfServiceIds.isEmpty()){
            logger.warn("api 工程初始化错误，没有自身注册服务数据");
            return;
        }

    }

    @Override
//    @HystrixCommand(fallbackMethod="requestApiFallBack")
    /**
     * 调用接口
     * @param appId
     * @param paramVo
     * @return
     */
    public OpenApiResVo requestApi(String appId,OpenApiRequestParamVo paramVo) {
        OpenApiResVo res = new OpenApiResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        String validField = "valid";
        String serviceField = "service";
        String serverAppField = "serverApp";
        Map<String,Object> validRes = validateInf(appId,res,paramVo);
        if(!(boolean)validRes.get(validField)){
            return res;
        }
        com.github.hollykunge.openapi.entity.Service apiService = (com.github.hollykunge.openapi.entity.Service)validRes.get(serviceField);
        App serverApp = (App)validRes.get(serverAppField);
        //request参数
        Map<String,String> mapParams = paramVo.getRequestParams();
        //requestBody
        Object requestBody = paramVo.getRequestBody();

          String mainUrl = serverApp.getMainUrl();
          String url = mainUrl+apiService.getRequestUrl();
          String requestType = apiService.getRequestType();
          String resMsg = "";
          HttpMethod requestTypeEnum = null;
            if(requestType.equals(ConfigConstants.REQUEST_TYPE_GET)){
                requestTypeEnum = HttpMethod.GET;
            }else if(requestType.equals(ConfigConstants.REQUEST_TYPE_POST)){
                requestTypeEnum = HttpMethod.POST;
            }else if(requestType.equals(ConfigConstants.REQUEST_TYPE_DELETE)){
                requestTypeEnum = HttpMethod.DELETE;
            }else if(requestType.equals(ConfigConstants.REQUEST_TYPE_PUT)){
                requestTypeEnum = HttpMethod.PUT;
            }else {
                String unKnownRequestType = "openApi未知请求";
                logger.error(unKnownRequestType+requestType);
                res.setCode(ConfigConstants.RES_ERROR_UNKNOWN_REQUEST_TYPE);
                res.setMsg(ConfigConstants.RES_ERROR_UNKNOWN_REQUEST_TYPE_MSG);
                return res;
            }
            if(requestTypeEnum!=null){
                // 请求头
                HttpHeaders headers = new HttpHeaders();
                String cType = CommonUtil.no2EmptyStr(apiService.getContentType());
                if(cType.equals(ConfigConstants.CONTENT_TYPE_X_WWW_FORM_URLENCODED)){
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                }else if(cType.equals(ConfigConstants.CONTENT_TYPE_JSON)){
                    headers.setContentType(MediaType.APPLICATION_JSON);
                }else {
                    headers.setContentType(MediaType.APPLICATION_JSON);
                }

                //如果是自身服务接口
                if(appSelfServiceIds.contains(paramVo.getServiceId())){
                    headers.set(ConfigConstants.API_TOKEN_HEADER,appSelfToken);
                }

                // 发送请求
                HttpEntity entity = new HttpEntity<>(requestBody,headers);
                // put请求
                ResponseEntity<String> resultEntity = null;
                try {
                    resultEntity = restTemplate.exchange(url,requestTypeEnum, entity,String.class, mapParams);
                } catch (RestClientException e) {
                    String errorMsg = "openApi 接口调用失败";
                    logger.error(errorMsg);
                    logger.error(CommonUtil.getExceptionMessage(e));
                    res.setCode(ConfigConstants.RES_ERROR_SYSTEM);
                    res.setMsg(ConfigConstants.RES_FAIL_MSG);
                    return res;
                }
                resMsg = resultEntity.getBody();
            }

         /* if(requestType.equals(ConfigConstants.REQUEST_TYPE_GET)){
              //get
              resMsg = restTemplate.getForObject(url, String.class,mapParams);
          }else if(requestType.equals(ConfigConstants.REQUEST_TYPE_POST)){
              //post
              *//*MultiValueMap<String, String> requestMap= new LinkedMultiValueMap<>(4);
              requestMap.add("client_id", "123");
              requestMap.add("app_code", "abc");*//*
              HttpEntity requestEntity = new HttpEntity(mapParams,null);
              resMsg = restTemplate.postForObject(url,requestEntity,String.class);
          }else if(requestType.equals(ConfigConstants.REQUEST_TYPE_DELETE)){
              //delete
              ResponseEntity<String > response = restTemplate.exchange(url, HttpMethod.DELETE, null, String .class, mapParams);
              resMsg = response.getBody();
          }else if(requestType.equals(ConfigConstants.REQUEST_TYPE_PUT)){
              //put
              // 请求头
              HttpHeaders headers = new HttpHeaders();
              headers.setContentType(MediaType.APPLICATION_JSON);
              // 发送请求
              HttpEntity entity = new HttpEntity<>(mapParams,headers);
              // put请求
              ResponseEntity<String> resultEntity = restTemplate.exchange(url, HttpMethod.PUT, entity,String.class, mapParams);
              resMsg = resultEntity.getBody();
              //restTemplate.delete(url,mapParams);
          }else {
              String unKnownRequestType = "openApi未知请求";
              logger.error(unKnownRequestType+requestType);
              res.setCode(ConfigConstants.RES_ERROR_UNKNOWN_REQUEST_TYPE);
              res.setMsg(ConfigConstants.RES_ERROR_UNKNOWN_REQUEST_TYPE_MSG);
          }*/

            try {
                if(resMsg!=null && !"".equals(resMsg)){
                    JSONObject json = JSONObject.parseObject(resMsg);
                    res.setRes(json);
                }
            } catch (Exception e) {
                String errorMsg = "openApi 返回结果转换json错误";
                logger.error(errorMsg + resMsg);
            }

        return res;
    }

    /**
     * 调用api接口失败熔断降级
     * @return
     */
    private OpenApiResVo requestApiFallBack(String appId,OpenApiRequestParamVo paramVo, Throwable exception){
        String errorMsg = "openApi 进入降级方法";
        String errorApp = "appid:";
        String errorService = "serviceid:";
        logger.error(errorApp + appId);
        logger.error(errorService + paramVo.getServiceId());
        logger.error(errorMsg);
        logger.error(CommonUtil.getExceptionMessage(exception));
        OpenApiResVo res = new OpenApiResVo();
        res.setCode(ConfigConstants.RES_ERROR_API_FALLBACK);
        res.setMsg(ConfigConstants.RES_ERROR_API_FALLBACK_MSG);
        res.setRes(paramVo);
        return  res;
    }

    /**
     * 校验请求信息是否合法
     * @param appId
     * @param apiResVo
     * @param paramVo
     * @return
     */
    private Map<String,Object> validateInf(String appId,OpenApiResVo apiResVo,OpenApiRequestParamVo paramVo){
        String validField = "valid";
        String serviceField = "service";
        String serverAppField = "serverApp";
        Map<String,Object> res = new HashMap<>(4);
        //校验应用是否可用
        Example exampleApp = new Example(App.class);
        Example.Criteria criteriaApp = exampleApp.createCriteria();
        String tableColName = "appId";
        criteriaApp.andEqualTo(tableColName, appId);
        String tableStatusName = "status";
        String canUse = "1";
        criteriaApp.andEqualTo(tableStatusName, canUse);
        List<App> listApp = appBiz.selectByExample(exampleApp);
        if(listApp.isEmpty()){
            apiResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_APP);
            apiResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_APP_MSG);
            res.put(validField,false);
            return res;
        }
        //校验接口是否可用
        if(paramVo==null || paramVo.getServiceId()==null || "".equals(paramVo.getServiceId())){
            apiResVo.setCode(ConfigConstants.RES_ERROR_REQUEST_PARAM_FORMAT);
            apiResVo.setMsg(ConfigConstants.RES_ERROR_REQUEST_PARAM_FORMAT_MSG);
            res.put(validField,false);
            return res;
        }
        String serviceId = paramVo.getServiceId();
        com.github.hollykunge.openapi.entity.Service service = serviceBiz.selectById(serviceId);
        String serviceCanUse = "1";
        if(service == null || !service.getStatus().equals(serviceCanUse)){
            apiResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_API);
            apiResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_API_MSG);
            res.put(validField,false);
            return res;
        }
        //校验服务app是否可用
        String serverCanUse = "1";
        Example exampleServerApp = new Example(App.class);
        Example.Criteria criteriaServer = exampleServerApp.createCriteria();
        String tableColServerApp = "appId";
        criteriaServer.andEqualTo(tableColServerApp, service.getAppId());
        String tableColServerStatus = "status";
        criteriaServer.andEqualTo(tableColServerStatus, serverCanUse);
        List<App> listServerApp = appBiz.selectByExample(exampleServerApp);
        if(listServerApp.isEmpty()){
            apiResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_APP);
            apiResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_APP_MSG);
            res.put(validField,false);
            return res;
        }
        App ServerApp = listServerApp.get(0);
        //校验是否有使用权限
        String applyCanUse = "1";
        Example example = new Example(Apply.class);
        Example.Criteria criteria = example.createCriteria();
        String tableColApp = "appId";
        criteria.andEqualTo(tableColApp, appId);
        String tableColService = "serviceId";
        criteria.andEqualTo(tableColService, serviceId);
        String tableColStatus = "status";
        criteria.andEqualTo(tableColStatus, applyCanUse);
        List<Apply> list = applyBiz.selectByExample(example);
        if(list.isEmpty()){
            apiResVo.setCode(ConfigConstants.RES_ERROR_AUTHORITY);
            apiResVo.setMsg(ConfigConstants.RES_ERROR_AUTHORITY_MSG);
            res.put(validField,false);
            return res;
        }
        res.put(validField,true);
        res.put(serviceField,service);
        res.put(serverAppField,ServerApp);
        return res;
    }
}
