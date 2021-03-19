package com.github.hollykunge.openapi.biz.business;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.entity.business.NoticeHeader;
import com.github.hollykunge.openapi.mapper.business.NoticeMapper;
import com.github.hollykunge.openapi.vo.business.NoticeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/3/18 16:15
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBiz   extends BaseBiz<NoticeMapper, NoticeHeader> {
    @Override
    protected String getPageName() {
        return null;
    }

    /**
     * 保存通知
     * @param noticeVo
     * @return
     */
    public String saveNotice(NoticeVo noticeVo){
        NoticeHeader header = new NoticeHeader();
        BeanUtils.copyProperties(noticeVo,header);
        String id = this.insertSelective(header);
        return ConfigConstants.RES_SUCCESS;
    }

}