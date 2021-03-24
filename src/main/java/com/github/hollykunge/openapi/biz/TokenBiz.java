package com.github.hollykunge.openapi.biz;

import com.github.hollykunge.openapi.biz.base.BaseBiz;
import com.github.hollykunge.openapi.entity.Token;
import com.github.hollykunge.openapi.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhuqz
 * @date: 2021/3/23 11:22
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TokenBiz  extends BaseBiz<TokenMapper, Token> {
    @Autowired
    TokenMapper tokenMapper;
    @Override
    protected String getPageName() {
        return null;
    }
    public String getSelfToken(String appId){
         return  tokenMapper.getSelfToken(appId);
    }
}