package com.github.hollykunge.openapi.biz;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.ApiLog;
import com.github.hollykunge.openapi.mapper.ApiLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/4/1 13:57
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiLogBiz  extends BaseBiz<ApiLogMapper, ApiLog> {
    @Override
    protected String getPageName() {
        return null;
    }
}