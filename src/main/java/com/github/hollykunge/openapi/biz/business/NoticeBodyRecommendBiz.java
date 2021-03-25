package com.github.hollykunge.openapi.biz.business;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.business.NoticeBodyRecommend;
import com.github.hollykunge.openapi.mapper.business.NoticeBodyRecommendMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/3/25 14:57
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBodyRecommendBiz      extends BaseBiz<NoticeBodyRecommendMapper, NoticeBodyRecommend> {
    @Override
    protected String getPageName() {
        return null;
    }
}