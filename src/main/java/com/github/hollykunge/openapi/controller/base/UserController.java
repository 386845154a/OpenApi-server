package com.github.hollykunge.openapi.controller.base;


import com.github.hollykunge.openapi.biz.UserBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.controller.base.base.BaseController;
import com.github.hollykunge.openapi.entity.User;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhuqz
 * @date: 2021/3/3 16:31
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz, User> {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ResVo> user(@RequestBody User user) {

       int i = baseBiz.addUser(user);
        int success = 1,exist = 0;
        ResVo res = new ResVo();
        if(i==exist){
            res.setCode(ConfigConstants.RES_ERROR_SYSTEM);
            res.setMsg(ConfigConstants.RES_FAIL_MSG);
        }
        return new ObjectRestResponse<>().data(res).rel(true);
    }
}