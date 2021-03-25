package com.github.hollykunge.openapi.biz.business;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.business.NoticeBodyShow;
import com.github.hollykunge.openapi.mapper.business.NoticeBodyShowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/3/25 14:51
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBodyShowBiz     extends BaseBiz<NoticeBodyShowMapper, NoticeBodyShow> {
    @Override
    protected String getPageName() {
        return null;
    }
}