package com.github.hollykunge.openapi.controller.base;

import com.github.hollykunge.openapi.biz.ApplyBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.controller.base.base.BaseController;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.entity.Apply;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhuqz
 * @date: 2020/6/22 14:08
 * @description: 申请
 */
@RestController
@RequestMapping("/apply")
public class ApplyController extends BaseController<ApplyBiz, Apply> {
    /**
     * 添加
     * @param apply
     * @return
     */
    @RequestMapping(value = "/saveApply", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ResVo> saveApply(@RequestBody Apply apply) {
        baseBiz.insertSelective(apply);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg(apply.getId());
        return new ObjectRestResponse<>().data(res).rel(true);
    }
    /**
     * 修改
     * @param apply
     * @return
     */
    @RequestMapping(value = "/updateApply", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<ResVo> updateApply(@RequestBody Apply apply) {
        baseBiz.updateSelectiveById(apply);
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
    @RequestMapping(value = "/removeApply", method = RequestMethod.DELETE)
    public ObjectRestResponse<ResVo> removeApply(@RequestParam("id") String id) {
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
    @RequestMapping(value = "/getApply", method = RequestMethod.GET)
    public ObjectRestResponse<String> getApply(@RequestParam("id") String id) {
        Apply apply = baseBiz.selectById(id);
        return new ObjectRestResponse<>().data(apply).rel(true);
    }
}