package com.github.hollykunge.openapi.vo.business;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2021/3/18 16:10
 * @description:
 */
@ApiModel("通知vo内容")
@Data
public class NoticeBodyVo implements Serializable {
    private static final long serialVersionUID = -6568059732593750863L;
}