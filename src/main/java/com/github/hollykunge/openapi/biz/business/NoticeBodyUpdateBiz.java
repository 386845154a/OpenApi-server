package com.github.hollykunge.openapi.biz.business;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.business.NoticeBodyUpdate;
import com.github.hollykunge.openapi.mapper.business.NoticeBodyUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/3/25 14:55
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBodyUpdateBiz    extends BaseBiz<NoticeBodyUpdateMapper, NoticeBodyUpdate> {
    @Override
    protected String getPageName() {
        return null;
    }
}