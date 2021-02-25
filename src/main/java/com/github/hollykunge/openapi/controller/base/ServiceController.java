package com.github.hollykunge.openapi.controller.base;

import com.github.hollykunge.openapi.biz.ServiceBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.controller.base.base.BaseController;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.entity.Service;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhuqz
 * @date: 2020/6/22 14:08
 * @description: 接口服务
 */
@RestController
@RequestMapping("/service")
public class ServiceController  extends BaseController<ServiceBiz, Service> {
    /**
     * 添加
     * @param service
     * @return
     */
    @RequestMapping(value = "/saveService", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ResVo> saveService(@RequestBody Service service) {
        baseBiz.insertSelective(service);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(service.getId());
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 修改
     * @param service
     * @return
     */
    @RequestMapping(value = "/updateService", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<ResVo> updateService(@RequestBody Service service) {
        baseBiz.updateSelectiveById(service);
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
    @RequestMapping(value = "/removeService", method = RequestMethod.DELETE)
    public ObjectRestResponse<ResVo> removeService(@RequestParam("id") String id) {
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
    @RequestMapping(value = "/getService", method = RequestMethod.GET)
    public ObjectRestResponse<Service> getService(@RequestParam("id") String id) {
        Service service = baseBiz.selectById(id);
        return new ObjectRestResponse<>().data(service).rel(true);
    }
}