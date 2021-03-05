package com.github.hollykunge.openapi.vo.base;

import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.config.UserAuthContonstants;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/22 09:12
 * @description: 基础功能操作结果（如注册app、接口审批、注册服务等等）
 * 该类不要再加属性，其他类继承该类扩展属性
 */
@Data
public class ResVo implements Serializable {
    private static final long serialVersionUID = -1021289967205770057L;
    /**
     * 编码（参考ConfigContants.java）
     */
    private String code = ConfigConstants.RES_SUCCESS;
    /**
     * 消息
     */
    private String msg = ConfigConstants.RES_SUCCESS_MSG;
}