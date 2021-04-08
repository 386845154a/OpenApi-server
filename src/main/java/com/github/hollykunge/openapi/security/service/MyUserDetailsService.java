package com.github.hollykunge.openapi.security.service;


import com.github.hollykunge.openapi.biz.UserBiz;
import com.github.hollykunge.openapi.biz.UserRoleBiz;
import com.github.hollykunge.openapi.entity.App;
import com.github.hollykunge.openapi.security.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<MyUserDetails> list = new ArrayList<>();
        List<App> users =  userBiz.getUserByName(username);
        if (users == null || users.isEmpty()){
            throw new UsernameNotFoundException("App User "+ username +" didn't exist.");
        }
        List<String> roles = userRoleBiz.getUserRoleList(username);
        //格式转化
        List<GrantedAuthority> authority = roles.stream().map(i->new SimpleGrantedAuthority(i)).collect(Collectors.toList());
        //用户锁定固定写死
        boolean lockflg = false;
        for(App user:users){
            list.add(new MyUserDetails(user.getCode(),user.getPwd(),lockflg,authority));
        }
        //返回第一个用户即可
        return list.get(0);
    }
    public List<MyUserDetails> loadUsersByUsername(String username) throws UsernameNotFoundException {
        List<MyUserDetails> list = new ArrayList<>();
        List<App> users =  userBiz.getUserByName(username);
        if (users == null || users.isEmpty()){
            throw new UsernameNotFoundException("App User "+ username +" didn't exist.");
        }
        List<String> roles = userRoleBiz.getUserRoleList(username);
        //格式转化
        List<GrantedAuthority> authority = roles.stream().map(i->new SimpleGrantedAuthority(i)).collect(Collectors.toList());
        //用户锁定固定写死
        boolean lockflg = false;
        for(App user:users){
            list.add(new MyUserDetails(user.getCode(),user.getPwd(),lockflg,authority));
        }
        return list;
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