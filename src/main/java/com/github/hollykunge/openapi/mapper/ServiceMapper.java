package com.github.hollykunge.openapi.mapper;

import com.github.hollykunge.openapi.entity.Service;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ServiceMapper extends Mapper<Service> {
    List<String>  getSelfAppServices(@Param("appId") String appId);
}
