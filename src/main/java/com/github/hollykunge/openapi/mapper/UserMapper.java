package com.github.hollykunge.openapi.mapper;


import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper   extends Mapper<User> {
    //User getUserByName(@Param("name") String name);
    List<App> getUserByName(@Param("name") String name);
}
