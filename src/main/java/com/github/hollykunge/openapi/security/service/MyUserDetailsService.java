package com.github.hollykunge.openapi.security.service;


import com.github.hollykunge.openapi.biz.UserBiz;
import com.github.hollykunge.openapi.biz.UserRoleBiz;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.entity.User;
import com.github.hollykunge.openapi.security.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private UserRoleBiz userRoleBiz;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        App user =  userBiz.getUserByName(username);
        if (user == null){
            throw new UsernameNotFoundException("App User "+ username +" didn't exist.");
        }
        //用户锁定固定写死
        boolean lockflg = false;
        return new MyUserDetails(user.getName(),user.getPwd(),lockflg,null);
    }
    /*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userBiz.getUserByName(username);
        if (user == null){
            throw new UsernameNotFoundException("User "+ username +" didn't exist.");
        }
        //获得角色信息
        List<String> roles = userRoleBiz.getUserRoleList(user.getId());
        //格式转化
        List<GrantedAuthority> authority = roles.stream().map(i->new SimpleGrantedAuthority(i)).collect(Collectors.toList());
        //用户锁定固定写死
        boolean lockflg = false;
        return new MyUserDetails(user.getName(),user.getPwd(),lockflg,authority);
    }*/
}