package com.github.hollykunge.openapi.biz;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhuqz
 * @date: 2020/6/22 14:04
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceBiz  extends BaseBiz<ServiceMapper, com.github.hollykunge.openapi.entity.Service> {
    @Autowired
    ServiceMapper serviceMapper;
    @Override
    protected String getPageName() {
        return null;
    }
    public List<String> getSelfAppServices(String appId){
        return this.serviceMapper.getSelfAppServices(appId);
    }
}