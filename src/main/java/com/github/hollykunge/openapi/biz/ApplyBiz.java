package com.github.hollykunge.openapi.biz;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.Apply;
import com.github.hollykunge.openapi.mapper.ApplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2020/6/22 14:01
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplyBiz  extends BaseBiz<ApplyMapper, Apply> {
    @Override
    protected String getPageName() {
        return null;
    }
}