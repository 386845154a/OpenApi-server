package com.github.hollykunge.openapi.mapper;

import com.github.hollykunge.openapi.entity.Token;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: zhuqz
 * @date: 2021/3/23 11:21
 * @description:
 */
public interface TokenMapper  extends Mapper<Token> {
    String getSelfToken(@Param("appId") String appId);
}