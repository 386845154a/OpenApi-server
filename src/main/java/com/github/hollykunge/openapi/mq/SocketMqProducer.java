package com.github.hollykunge.openapi.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hollykunge.openapi.config.CommonUtil;
import com.github.hollykunge.openapi.config.UUIDUtils;
import com.github.hollykunge.openapi.config.business.MessageType;
import com.github.hollykunge.openapi.config.business.RabbiMqExchangeConstant;
import com.github.hollykunge.openapi.config.business.RabbitMqRoutingKeyConstant;
import com.github.hollykunge.openapi.config.business.SocketMsgTypeEnum;
import com.github.hollykunge.openapi.vo.socket.SocketMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: zhuqz
 * @date: 2020/6/2 16:19
 * @description: 信息中心发送消息
 */
//@Component
//public class SocketMqProducer implements RabbitTemplate.ConfirmCallback {
public class SocketMqProducer  {
   /* private final Logger logger = LoggerFactory.getLogger(this.getClass());
    *//**由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入*//*
    private RabbitTemplate rabbitTemplate;
    *//**
     * 构造方法注入rabbitTemplate
     *//*
    @Autowired
    public SocketMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }
    *//**
     * 发送socket消息
     * @param msgVo
     * @return
     *//*
    public boolean sendSocketMsg(SocketMsgVo msgVo){
        String scode = msgVo.getCode();
        if(scode==null){
            logger.error("socket 消息错误 没有一级编码");
            return false;
        }
        CorrelationData correlationId = new CorrelationData(UUIDUtils.generateShortUuid());
        //消息发送与接收确认机制begin
       *//* int init_count = 0;
        MQMsgModel mqMsgModel =  this.getMqMsgModel(correlationId.getId(),init_count, MessageType.NEWS_SENDING,new Date(),msgVo);
        zzMsgCenterConfirmService.saveMsgConfirm(mqMsgModel);*//*
        //消息发送与接收确认机制end
        doSendMsg(msgVo,correlationId);
        return  true;
    }

    *//**
     * 发送消息
     * @param msgVo
     * @param correlationId
     * @return
     *//*
    private boolean doSendMsg(SocketMsgVo msgVo,CorrelationData correlationId) {
        String scode = msgVo.getCode();

        MessageProperties properties = new MessageProperties();
        properties.setCorrelationId(correlationId.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(scode.equals(SocketMsgTypeEnum.SINGLE_MSG.getCode())){
                //单人消息
                rabbitTemplate.convertAndSend(RabbiMqExchangeConstant.EXCHANGE_SOCKET, RabbitMqRoutingKeyConstant.ROUTINGKEY_SOCKET_PRIVATE_MSG, MessageBuilder.withBody(objectMapper.writeValueAsBytes(msgVo)).andProperties(properties).build(), correlationId);
            }else if(scode.equals(SocketMsgTypeEnum.TEAM_MSG.getCode())){
                //群体消息
                rabbitTemplate.convertAndSend(RabbiMqExchangeConstant.EXCHANGE_SOCKET, RabbitMqRoutingKeyConstant.ROUTINGKEY_SOCKET_TEAM_MSG, MessageBuilder.withBody(objectMapper.writeValueAsBytes(msgVo)).andProperties(properties).build(), correlationId);
            }else if(scode.equals(SocketMsgTypeEnum.BIND_USER.getCode())){
                //绑定
                rabbitTemplate.convertAndSend(RabbiMqExchangeConstant.EXCHANGE_SOCKET, RabbitMqRoutingKeyConstant.ROUTINGKEY_SOCKET_TEAM_BIND, MessageBuilder.withBody(objectMapper.writeValueAsBytes(msgVo)).andProperties(properties).build(), correlationId);
            }else if(scode.equals(SocketMsgTypeEnum.UNBIND_USER.getCode())){
                //解绑
                rabbitTemplate.convertAndSend(RabbiMqExchangeConstant.EXCHANGE_SOCKET, RabbitMqRoutingKeyConstant.ROUTINGKEY_SOCKET_TEAM_UNBIND, MessageBuilder.withBody(objectMapper.writeValueAsBytes(msgVo)).andProperties(properties).build(), correlationId);
            } else if(scode.equals(SocketMsgTypeEnum.BIND_LIST.getCode())){
                //群体列表绑定
                rabbitTemplate.convertAndSend(RabbiMqExchangeConstant.EXCHANGE_SOCKET, RabbitMqRoutingKeyConstant.ROUTINGKEY_SOCKET_TEAMLIST_BIND, MessageBuilder.withBody(objectMapper.writeValueAsBytes(msgVo)).andProperties(properties).build(), correlationId);
            }else {
                logger.error("socket 消息错误 一级编码无法识别");
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("socket 消息发送错误");
            logger.error(CommonUtil.getExceptionMessage(e));
        } catch (Exception ex){
            logger.error("socket 消息发送错误:"+correlationId+","+msgVo.toString());
            logger.error(CommonUtil.getExceptionMessage(ex));
            throw ex;
        }
        return true;
    }

    *//**
     * 消息是否到交换机中都有callback
     *//*
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("[唯一标识]  " + correlationData);
        *//*String markId = correlationData.getId();
        MQMsgModel mqMsgModel = new MQMsgModel();
        mqMsgModel.setMarks(markId);
        if (ack) {
            logger.info("[消息到交换机结果]  " + "成功");
            mqMsgModel.setStatus(MessageType.NEWS_BORKDER_SUCCESS);
        } else {
            logger.info("[失败原因]  " + cause);
            mqMsgModel.setStatus(MessageType.NEWS_BORKDER_FAILURE);
        }
        //更新消息状态
        this.zzMsgCenterConfirmService.updateMsgConfirmWithOutConsumer(mqMsgModel);*//*
    }*/
}