package com.github.hollykunge.openapi.biz;

import cn.hutool.core.date.DateUtil;
import com.github.hollykunge.openapi.auth.ApiToken;
import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.config.UUIDUtils;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.entity.Apply;
import com.github.hollykunge.openapi.mapper.AuthMapper;
import com.github.hollykunge.openapi.vo.auth.RegisterResVo;
import com.github.hollykunge.openapi.vo.auth.TokenParamVo;
import com.github.hollykunge.openapi.vo.auth.TokenResVo;
import com.github.hollykunge.openapi.vo.base.ResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhuqz
 * @date: 2020/6/28 13:37
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthBiz   extends BaseBiz<AuthMapper, App> {
    @Autowired
    private AppBiz appBiz;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    protected String getPageName() {
        return null;
    }
    @Autowired
    private ServiceBiz serviceBiz;
    @Autowired
    private ApplyBiz applyBiz;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 注册app
     * @param app
     * @return
     */
    public RegisterResVo registerApp(App app){
        RegisterResVo registerResVo = new RegisterResVo();
        registerResVo.setCode(ConfigConstants.RES_SUCCESS);
        registerResVo.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        if(app == null || app.getName() == null || "".equals(app.getName())  || app.getMainUrl() == null || "".equals(app.getMainUrl()) || "".equals(app.getPwd())){
            registerResVo.setCode(ConfigConstants.RES_ERROR_REGISTER_APP_NULL);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_REGISTER_APP_NULL_MSG);
            return registerResVo;
        }
        Example example = new Example(App.class);
        Example.Criteria criteria = example.createCriteria();
        String tableColName = "name";
        criteria.andEqualTo(tableColName, app.getName());
        List<App> list = appBiz.selectByExample(example);
        if(!list.isEmpty()){
            registerResVo.setCode(ConfigConstants.RES_ERROR_REGISTER_NAME_EXIST);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_REGISTER_NAME_EXIST_MSG);
            return registerResVo;
        }
        Example exampleUrl = new Example(App.class);
        Example.Criteria criteriaUrl = exampleUrl.createCriteria();
        String tableColMainUrl = "mainUrl";
        criteriaUrl.andEqualTo(tableColMainUrl, app.getMainUrl());
        List<App> listUrl = appBiz.selectByExample(exampleUrl);
        if(!listUrl.isEmpty()){
            registerResVo.setCode(ConfigConstants.RES_ERROR_REGISTER_MAIN_URL_EXIST);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_REGISTER_MAIN_URL_EXIST_MSG);
            return registerResVo;
        }

        //appId
        String appId = UUIDUtils.generateShortUuid();
        //appSecret
        String appSecret = generateAppSecrect(appId);
        registerResVo.setAppId(appId);
        registerResVo.setAppSecret(appSecret);
        app.setAppId(appId);
        app.setSecret(appSecret);
        //登录密码
        app.setPwd(passwordEncoder.encode(app.getPwd()));

        appBiz.insertSelective(app);
        return registerResVo;
    }

    /**
     * 生成AppSecret
     * @param appId
     * @return
     */
    private String generateAppSecrect(String appId){
        String dateStr = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        //使用时间戳 md5 映射一下 作为AppSecret
        String md5Password = DigestUtils.md5DigestAsHex((appId+dateStr).getBytes());
        return md5Password;
    }

    /**
     * 获取token
     * @param appVo
     * @return
     */
    public TokenResVo getToken(TokenParamVo appVo){
        TokenResVo tokenResVo = new TokenResVo();
        tokenResVo.setCode(ConfigConstants.RES_SUCCESS);
        tokenResVo.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        validInf(appVo,tokenResVo);
        if(tokenResVo.getCode()!= ConfigConstants.RES_SUCCESS){
            return tokenResVo;
        }
        ApiToken apiToken = generateApiToken(appVo.getAppId());
        tokenResVo.setToken(apiToken.getAccessToken());
        return tokenResVo;
    }

    /**
     * 生成token
     * @param appId
     * @return
     */
    private ApiToken generateApiToken(String appId){
        //过期时间两个小时
        long expireTimeUnit = 2*3600L;
        long crtTime = System.currentTimeMillis();
        int randomLength = 40;
        ApiToken apiToken = new ApiToken();
        apiToken.setAppId(appId);
        apiToken.setCrtTime(crtTime);
        apiToken.setExpireTime(crtTime+ expireTimeUnit) ;
        String accessToken = UUIDUtils.generateShortUuid()+getRandomString(randomLength);
        apiToken.setAccessToken(accessToken);
        //key是token
        redisTemplate.opsForValue().set(ConfigConstants.RESDIS_TOKEN_COLLECTION+accessToken,apiToken,expireTimeUnit,TimeUnit.SECONDS);
        return apiToken;
    }
    private void validInf(TokenParamVo appVo,TokenResVo tokenResVo){
        if(appVo == null || appVo.getAppId() == null || "".equals(appVo.getAppSecret()) || appVo.getAppSecret() ==null || "".equals(appVo.getAppSecret())){
            tokenResVo.setCode(ConfigConstants.RES_ERROR_GET_TOKEN_APP_NULL);
            tokenResVo.setMsg(ConfigConstants.RES_ERROR_GET_TOKEN_APP_NULL_MSG);
            return;
        }
        Example example = new Example(App.class);
        Example.Criteria criteria = example.createCriteria();
        String tableColName = "appId";
        criteria.andEqualTo(tableColName, appVo.getAppId());
        String tableStatusName = "status";
        String canUse = "1";
        criteria.andEqualTo(tableStatusName, canUse);
        List<App> list = appBiz.selectByExample(example);
        if(list.isEmpty()){
            tokenResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_APP);
            tokenResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_APP_MSG);
            return;
        }
        App app = list.get(0);
        String appSecretInDataBase = app.getSecret();
        String appSecretParam = appVo.getAppSecret();
        if(!appSecretInDataBase.equals(appSecretParam)){
            tokenResVo.setCode(ConfigConstants.RES_ERROR_GET_TOKEN_APP_SECRET);
            tokenResVo.setMsg(ConfigConstants.RES_ERROR_GET_TOKEN_APP_SECRET_MSG);
            return;
        }
    }
    private String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 注册服务api
     * @param service
     * @return
     */
    public ResVo registerService(com.github.hollykunge.openapi.entity.Service service){
        ResVo registerResVo = new ResVo();
        registerResVo.setCode(ConfigConstants.RES_SUCCESS);
        registerResVo.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        if(service == null ||
                service.getRequestType() == null || "".equals(service.getRequestType())||
                service.getRequestUrl() == null || "".equals(service.getRequestUrl())||
                service.getAppId() == null || "".equals(service.getAppId())
        ){
            registerResVo.setCode(ConfigConstants.RES_ERROR_REGISTER_SERVICE_NULL);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_REGISTER_SERVICE_NULL_MSG);
            return registerResVo;
        }
        //判断app是否可以
        Example exampleApp = new Example(App.class);
        Example.Criteria criteriaApp = exampleApp.createCriteria();
        String tableColName = "appId";
        criteriaApp.andEqualTo(tableColName, service.getAppId());
        String tableStatusName = "status";
        String canUse = "1";
        criteriaApp.andEqualTo(tableStatusName, canUse);
        List<App> listApp = appBiz.selectByExample(exampleApp);
        if(listApp.isEmpty()){
            registerResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_APP);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_APP_MSG);
            return registerResVo;
        }
        //注册服务
        Example example = new Example(com.github.hollykunge.openapi.entity.Service.class);
        Example.Criteria criteria = example.createCriteria();
        String tableColApp = "appId";
        String tableColRequestType = "requestType";
        String tableColRequestUrl = "requestUrl";
        criteria.andEqualTo(tableColApp, service.getAppId());
        criteria.andEqualTo(tableColRequestType, service.getRequestType().toLowerCase());
        criteria.andEqualTo(tableColRequestUrl, service.getRequestUrl());
        List<com.github.hollykunge.openapi.entity.Service> list = serviceBiz.selectByExample(example);
        if(!list.isEmpty()){
            registerResVo.setCode(ConfigConstants.RES_ERROR_REGISTER_API_EXIST);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_REGISTER_API_EXIST_MSG);
            return registerResVo;
        }
        service.setRequestType(service.getRequestType().toLowerCase());
        serviceBiz.insertSelective(service);
        return registerResVo;
    }

    /**
     * 申请调用服务
     * @param apply
     * @return
     */
    public ResVo applyRequestApi(Apply apply){
        ResVo registerResVo = new ResVo();
        registerResVo.setCode(ConfigConstants.RES_SUCCESS);
        registerResVo.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        if(apply == null ||
                apply.getServiceId() == null || "".equals(apply.getServiceId())||
                apply.getAppId() == null || "".equals(apply.getAppId())
        ){
            registerResVo.setCode(ConfigConstants.RES_ERROR_APPLY_NULL);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_APPLY_NULL_MSG);
            return registerResVo;
        }
        //判断当前app是否可用或者存在
        Example exampleApp = new Example(App.class);
        Example.Criteria criteriaApp = exampleApp.createCriteria();
        String tableColName = "appId";
        criteriaApp.andEqualTo(tableColName, apply.getAppId());
        String tableStatusName = "status";
        String canUse = "1";
        criteriaApp.andEqualTo(tableStatusName, canUse);
        List<App> listApp = appBiz.selectByExample(exampleApp);
        if(listApp.isEmpty()){
            registerResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_APP);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_APP_MSG);
            return registerResVo;
        }
        //判断服务api是否可用或存在
        com.github.hollykunge.openapi.entity.Service service = serviceBiz.selectById(apply.getServiceId());
        String serviceCanUse = "1";
        if(service==null || service.getStatus()==null || !serviceCanUse.equals(service.getStatus())){
            registerResVo.setCode(ConfigConstants.RES_ERROR_NOT_EXISTS_API);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_NOT_EXISTS_API_MSG);
            return registerResVo;
        }
        //判断申请是否已经存在
        Example exampleApply = new Example(Apply.class);
        Example.Criteria criteriaApply = exampleApply.createCriteria();
        String tableApplyApp = "appId";
        criteriaApply.andEqualTo(tableApplyApp, apply.getAppId());
        String tableApplyService = "serviceId";
        criteriaApply.andEqualTo(tableApplyService, apply.getServiceId());
        List<Apply> listApply = applyBiz.selectByExample(exampleApply);
        if(!listApply.isEmpty()){
            registerResVo.setCode(ConfigConstants.RES_ERROR_APPLY_API_EXIST);
            registerResVo.setMsg(ConfigConstants.RES_ERROR_APPLY_API_EXIST_MSG);
            return registerResVo;
        }
        //添加申请
        applyBiz.insertSelective(apply);
        return registerResVo;
    }
}
