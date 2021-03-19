package com.github.hollykunge.openapi.biz;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.mapper.AppMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2020/6/19 16:23
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AppBiz  extends BaseBiz<AppMapper, App> {
    @Override
    protected String getPageName() {
        return null;
    }
    @Override
    public String insertSelective(App entity) {
        return super.insertSelective(entity);
    }
}