package com.github.hollykunge.openapi.controller.auth;

import com.github.hollykunge.openapi.biz.AuthBiz;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.entity.Apply;
import com.github.hollykunge.openapi.entity.Service;
import com.github.hollykunge.openapi.vo.auth.RegisterResVo;
import com.github.hollykunge.openapi.vo.auth.TokenParamVo;
import com.github.hollykunge.openapi.vo.auth.TokenResVo;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * @author: zhuqz
 * @date: 2020/6/28 13:32
 * @description: token管理
 */
@Api(tags="应用、服务注册，申请接口访问权限，获取token")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthBiz authBiz;
    /**
     * 注册应用
     * @param app 填写名称 描述 mainUrl
     * @return
     */
    @RequestMapping(value = "/registerApp", method = RequestMethod.POST)
    @ApiOperation(value = "添加注册app", notes = "进行应用注册，获取系统分配的应用ID", httpMethod = "POST")
    @ResponseBody
    public ObjectRestResponse<RegisterResVo> registerApp(@RequestBody App app) {
        RegisterResVo registerResVo = authBiz.registerApp(app);
        return new ObjectRestResponse<>().data(registerResVo).rel(true);
    }
    /**
     * 注册接口服务
     * @param service 填写appId requestType url
     * @return
     */
    @RequestMapping(value = "/registerService", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ResVo> registerService(@RequestBody Service service) {
        ResVo registerResVo = authBiz.registerService(service);
        return new ObjectRestResponse<>().data(registerResVo).rel(true);
    }
    /**
     * 申请调用服务api
     * @param apply 填写appId 接口serviceId
     * @return
     */
    @RequestMapping(value = "/applyRequestApi", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ResVo> applyRequestApi(@RequestBody Apply apply) {
        ResVo registerResVo = authBiz.applyRequestApi(apply);
        return new ObjectRestResponse<>().data(registerResVo).rel(true);
    }
    /**
     * 获取token
     * @param appVo 填写appid 和 appSecret
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<TokenResVo> getToken(@RequestBody TokenParamVo appVo) {
        TokenResVo tokenResVo = authBiz.getToken(appVo);
        return new ObjectRestResponse<>().data(tokenResVo).rel(true);
    }
}