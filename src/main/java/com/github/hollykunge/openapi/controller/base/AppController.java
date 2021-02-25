package com.github.hollykunge.openapi.controller.base;

import com.github.hollykunge.openapi.biz.AppBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.config.UUIDUtils;
import com.github.hollykunge.openapi.controller.base.base.BaseController;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhuqz
 * @date: 2020/6/19 16:27
 * @description: 应用
 */
@Api(tags="应用注册")
@RestController
@RequestMapping("/app")
public class AppController  extends BaseController<AppBiz, App> {

    /**
     * 添加
     * @param app
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "添加注册app", notes = "进行应用注册，获取系统分配的应用ID", httpMethod = "POST")
    @ResponseBody
    public ObjectRestResponse<ResVo> saveApp(@RequestBody App app) {
        app.setAppId(UUIDUtils.generateShortUuid());
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
        baseBiz.updateSelectiveById(app);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeApp", method = RequestMethod.DELETE)
    public ObjectRestResponse<ResVo> removeApp(@RequestParam("id") String id) {
        baseBiz.deleteById(id);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(ConfigConstants.RES_SUCCESS_MSG);
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/getApp", method = RequestMethod.GET)
    public ObjectRestResponse<String> getApp(@RequestParam("id") String id) {
        App app = baseBiz.selectById(id);
        return new ObjectRestResponse<>().data(app).rel(true);
    }
}
