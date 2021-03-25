package com.github.hollykunge.openapi.biz.business;

import cn.hutool.core.bean.BeanUtil;
import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.config.business.BusinessConstants;
import com.github.hollykunge.openapi.entity.business.*;
import com.github.hollykunge.openapi.mapper.business.NoticeMapper;
import com.github.hollykunge.openapi.mq.SocketMqProducer;
import com.github.hollykunge.openapi.vo.business.NoticeVo;
import com.github.hollykunge.openapi.vo.business.send.NoticeSendBodyApproveVo;
import com.github.hollykunge.openapi.vo.business.send.NoticeSendHeaderVo;
import com.github.hollykunge.openapi.vo.socket.SocketMsgDetailVo;
import com.github.hollykunge.openapi.vo.socket.SocketMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author: zhuqz
 * @date: 2021/3/18 16:15
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBiz   extends BaseBiz<NoticeMapper, NoticeHeader> {
    Logger logger = LoggerFactory.getLogger(NoticeBiz.class);
    @Autowired
    NoticeBodyApproveBiz noticeBodyApproveBiz;
    @Autowired
    NoticeBodyShowBiz noticeBodyShowBiz;
    @Autowired
    NoticeBodyUpdateBiz noticeBodyUpdateBiz;
    @Autowired
    NoticeBodyRecommendBiz noticeBodyRecommendBiz;
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
        //发送sockent协议
        SocketMsgVo socketMsgVo = new SocketMsgVo();
        socketMsgVo.setReceiver(noticeVo.getReceiverId());
        //openapi 通知协议
        SocketMsgDetailVo detailVo = new SocketMsgDetailVo();
        socketMsgVo.setMsg(detailVo);
        //发送通知头
        NoticeSendHeaderVo sendHeaderVo = new NoticeSendHeaderVo();
        detailVo.setData(sendHeaderVo);
        //将参数转换到发送数据里
        BeanUtil.copyProperties(noticeVo,sendHeaderVo);

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
            //通知具体类型
            NoticeSendBodyApproveVo bodyVo = new NoticeSendBodyApproveVo();
            BeanUtil.fillBeanWithMap(bodyMap, bodyVo, false);
            sendHeaderVo.setMsgContent(bodyVo);

        }
        //系统消息
        if(type == BusinessConstants.NOTICE_TYPE_SYS){
            NoticeBodyShow bodyEntity = new NoticeBodyShow();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            //业务id
            bodyEntity.setBusinessId(bodyMap.get("id").toString());
            bodyId = this.noticeBodyShowBiz.insertSelective(bodyEntity);
        }
        //数据更新
        if(type == BusinessConstants.NOTICE_TYPE_UPDATE){
            NoticeBodyUpdate bodyEntity = new NoticeBodyUpdate();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            bodyId = this.noticeBodyUpdateBiz.insertSelective(bodyEntity);
        }
        //推荐
        if(type == BusinessConstants.NOTICE_TYPE_RECOMMEND){
            NoticeBodyRecommend bodyEntity = new NoticeBodyRecommend();
            BeanUtil.fillBeanWithMap(bodyMap, bodyEntity, false);
            /*//时间
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sf.parse(bodyMap.get("time").toString());
                bodyEntity.setTime(date);
            } catch (ParseException e) {
                logger.error("通知 推荐 时间格式错误！！！");
                bodyEntity.setTime(new Date());
            }*/
            bodyId = this.noticeBodyRecommendBiz.insertSelective(bodyEntity);
        }

        NoticeHeader header = new NoticeHeader();
        BeanUtils.copyProperties(noticeVo,header);
        header.setDetailId(bodyId);
        String id = this.insertSelective(header);

        sendHeaderVo.setNoticeId(id);


        socketMqProducer.sendSocketMsg(socketMsgVo);
        return BusinessConstants.SUCCESS;
    }

}