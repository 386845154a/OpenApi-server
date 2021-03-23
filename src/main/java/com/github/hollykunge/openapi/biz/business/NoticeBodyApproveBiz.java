package com.github.hollykunge.openapi.biz.business;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.business.NoticeBodyOperate;
import com.github.hollykunge.openapi.mapper.business.NoticeBodyApproveMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/3/22 17:13
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBodyApproveBiz    extends BaseBiz<NoticeBodyApproveMapper, NoticeBodyOperate> {
    @Override
    protected String getPageName() {
        return null;
    }
}