package com.github.hollykunge.openapi.controller.business;

import com.github.hollykunge.openapi.biz.business.NoticeBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.controller.base.base.BaseController;
import com.github.hollykunge.openapi.entity.business.NoticeHeader;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.business.NoticeVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhuqz
 * @date: 2021/3/18 16:14
 * @description:
 */
@Api(tags="通知")
@RestController
@RequestMapping("/notice")
public class NoticeController   extends BaseController<NoticeBiz, NoticeHeader> {
    @Autowired
    private NoticeBiz noticeBiz;

    @RequestMapping(value = "/sendNotice", method = RequestMethod.POST)
    @ApiOperation(value = "发送通知", notes = "包括信息头，信息内容", httpMethod = "POST")
    @ResponseBody
    /**
     * 发送通知
     */
    public ObjectRestResponse<ResVo> sendNotice(@RequestBody NoticeVo noticeVo) {

        int resStr = this.noticeBiz.saveNotice(noticeVo);
        ResVo res = new ResVo();
        res.setCode(ConfigConstants.RES_SUCCESS);
        res.setMsg("");

        return new ObjectRestResponse<>().data(res).rel(true);
    }
}