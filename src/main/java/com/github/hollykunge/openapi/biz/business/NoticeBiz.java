package com.github.hollykunge.openapi.biz.business;

import cn.hutool.core.bean.BeanUtil;
import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.config.business.BusinessConstants;
import com.github.hollykunge.openapi.config.business.SocketMsgDetailTypeEnum;
import com.github.hollykunge.openapi.entity.business.NoticeBodyOperate;
import com.github.hollykunge.openapi.entity.business.NoticeHeader;
import com.github.hollykunge.openapi.mapper.business.NoticeMapper;
import com.github.hollykunge.openapi.mq.SocketMqProducer;
import com.github.hollykunge.openapi.vo.business.NoticeBodyApproveVo;
import com.github.hollykunge.openapi.vo.business.NoticeBodyVo;
import com.github.hollykunge.openapi.vo.business.NoticeVo;
import com.github.hollykunge.openapi.vo.socket.SocketMsgDetailVo;
import com.github.hollykunge.openapi.vo.socket.SocketMsgVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author: zhuqz
 * @date: 2021/3/18 16:15
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBiz   extends BaseBiz<NoticeMapper, NoticeHeader> {
    @Autowired
    NoticeBodyApproveBiz noticeBodyApproveBiz;
    @Autowired
    SocketMqProducer socketMqProducer;
    @Override
    protected String getPageName() {
        return null;
    }

    /**
     * 保存通知
     * @param noticeVo
     * @return
     */
    public int  saveNotice(NoticeVo noticeVo){
        SocketMsgVo socketMsgVo = new SocketMsgVo();
        SocketMsgDetailVo detailVo = new SocketMsgDetailVo();
        socketMsgVo.setMsg(detailVo);
        int noType = 2;
        String bodyId = "";
        Integer type = noticeVo.getSenderType();
        if(type == null){
            return noType;
        }
        Map bodyMap = (Map) noticeVo.getMsgContent();
        //审批通知
        if(type == BusinessConstants.NOTICE_TYPE_APPROVE){
            NoticeBodyOperate bodyEntity = new NoticeBodyOperate();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            //业务id
            bodyEntity.setBusinessId(bodyMap.get("id").toString());
            bodyId = this.noticeBodyApproveBiz.insertSelective(bodyEntity);
        }
        //系统消息
        if(type == BusinessConstants.NOTICE_TYPE_SYS){
            NoticeBodyOperate bodyEntity = new NoticeBodyOperate();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            //业务id
            bodyEntity.setBusinessId(bodyMap.get("id").toString());
            bodyId = this.noticeBodyApproveBiz.insertSelective(bodyEntity);
        }
        //数据更新
        if(type == BusinessConstants.NOTICE_TYPE_UPDATE){
            NoticeBodyOperate bodyEntity = new NoticeBodyOperate();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            //业务id
            bodyEntity.setBusinessId(bodyMap.get("id").toString());
            bodyId = this.noticeBodyApproveBiz.insertSelective(bodyEntity);
        }
        //推荐
        if(type == BusinessConstants.NOTICE_TYPE_RECOMMEND){
            NoticeBodyOperate bodyEntity = new NoticeBodyOperate();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            //业务id
            bodyEntity.setBusinessId(bodyMap.get("id").toString());
            bodyId = this.noticeBodyApproveBiz.insertSelective(bodyEntity);
        }

        NoticeHeader header = new NoticeHeader();
        BeanUtils.copyProperties(noticeVo,header);
        header.setDetailId(bodyId);
        String id = this.insertSelective(header);
        socketMqProducer.sendSocketMsg(socketMsgVo);
        return BusinessConstants.SUCCESS;
    }

}