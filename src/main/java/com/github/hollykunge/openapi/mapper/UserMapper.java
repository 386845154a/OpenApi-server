package com.github.hollykunge.openapi.mapper;


import com.github.hollykunge.openapi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper   extends Mapper<User> {
    User getUserByName(@Param("name") String name);
}
