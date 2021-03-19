package com.github.hollykunge.openapi.controller.base;

import com.github.hollykunge.openapi.biz.AppBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.config.UUIDUtils;
import com.github.hollykunge.openapi.controller.base.base.BaseController;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ListRestResponse;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author: zhuqz
 * @date: 2020/6/19 16:27
 * @description: 应用
 */
@Api(tags="应用管理")
@RestController
@RequestMapping("/app")
public class AppController  extends BaseController<AppBiz, App> {
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 应用登录
     * @param
     * @return
     *//*
    @ApiOperation(value = "登录平台", notes = "以应用的身份登录平台", httpMethod = "GET")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ObjectRestResponse<String> login(@RequestParam("id") String id) {
        App app = baseBiz.selectById(id);
        return new ObjectRestResponse<>().data(app).rel(true);
    }*/
    /**
     * 注册
     * @param app
     * @return
     */
    //@RequestMapping(value = "/register", method = RequestMethod.POST)
    @RequestMapping(value = "/saveApp", method = RequestMethod.POST)
    //@ApiOperation(value = "添加注册app", notes = "进行应用注册，获取系统分配的应用ID", httpMethod = "POST")
    @ResponseBody
    public ObjectRestResponse<ResVo> saveApp(@RequestBody App app) {
       // app.setAppId(UUIDUtils.generateShortUuid());
        baseBiz.insertSelective(app);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(app.getId());
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 修改
     * @param app
     * @return
     */
    @RequestMapping(value = "/updateApp", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<ResVo> updateApp(@RequestBody App app) {
        App appQuery = new App();
        appQuery.setAppId(app.getAppId());
        appQuery.setStatus("1");
        App newApp = baseBiz.selectList(appQuery).get(0);
        String id = newApp.getId();
        app.setId(id);
        ResVo res = new ResVo();
        if(app.getPwd()!=null && "".equals(app.getPwd())){
            app.setPwd(passwordEncoder.encode(app.getPwd()));
        }
        baseBiz.updateSelectiveById(app);
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 删除
     * @param appId
     * @return
     */
    @RequestMapping(value = "/removeApp", method = RequestMethod.DELETE)
    public ObjectRestResponse<ResVo> removeApp(@RequestParam("appId") String appId) {

        App appQuery = new App();
        appQuery.setAppId(appId);
        appQuery.setStatus("1");
        App newApp = baseBiz.selectList(appQuery).get(0);
        String id = newApp.getId();
        baseBiz.deleteById(id);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/getApp", method = RequestMethod.GET)
    public ObjectRestResponse<String> getApp(@RequestParam("name") String name) {
        App appQuery = new App();
        appQuery.setName(name);
        appQuery.setStatus("1");
        List<App> apps = baseBiz.selectList(appQuery);
        if(apps.isEmpty()){
            return new ObjectRestResponse<>().msg("未找到app").rel(false);
        }
        App app = apps.get(0);
        //前端使用appid字段，不使用id
        app.setId("");
        app.setPwd("");
        app.setSecret("");
        return new ObjectRestResponse<>().data(app).rel(true);
    }

    /**
     * 查询app列表
     * @return
     */
    @RequestMapping(value = "/getAppList", method = RequestMethod.GET)
    public ListRestResponse<App> getAppList() {
        App appQuery = new App();
        appQuery.setStatus("1");
        List<App> apps = baseBiz.selectList(appQuery);
        apps.stream().forEach((app)->{app.setId("");app.setPwd("");app.setSecret("");});
        ListRestResponse<App> restResponse = new ListRestResponse("",apps.size(),apps);
        return restResponse;
    }
}
