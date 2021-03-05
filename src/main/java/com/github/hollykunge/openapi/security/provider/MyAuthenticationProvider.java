package com.github.hollykunge.openapi.security.provider;


import com.github.hollykunge.openapi.biz.UserBiz;
import com.github.hollykunge.openapi.redis.RedisUtil;
import com.github.hollykunge.openapi.security.jwt.JwtProperties;
import com.github.hollykunge.openapi.security.jwt.JwtTokenUtil;
import com.github.hollykunge.openapi.security.service.MyUserDetailsService;
import com.github.hollykunge.openapi.security.util.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MyUserDetailsService myUserDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserBiz userBiz;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //根据输入的用户密码，读取数据库中信息
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        MyUserDetails user = (MyUserDetails) myUserDetailsService.loadUserByUsername(username);

        //判断是否有效用户
        if (!user.isEnabled()) {
            throw new DisabledException("USER DISABLE");
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException("USER LOCKED");
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("USER EXPIRED");
        } else if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("USER LOGOUT");
        }

        //验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("PASSWORD INVALID!");
        }

        logger.info(String.format("用户%s登录成功", username));
        /**
         * 前后端分离begin
         */
        // 生成一个新token
        /*String token = jwtTokenUtil.generateToken(user);
        // 需要持久化的话，那就将token保存到数据库，当然保存到redis更好
        User userData = this.userBiz.getUserByName(username);
        userData.setToken(token);
        this.userBiz.updateSelectiveById(userData);
        // 绑定到当前用户
        user.setToken(token);*/
        // 生成新token
        Map<String,String> tokens = jwtTokenUtil.generateToken(user);
        String accesstoken = tokens.get(jwtTokenUtil.getAccessTokenKey());
        String refreshtoken = tokens.get(jwtTokenUtil.getRefreshTokenKey());
        String rolestoken = tokens.get(jwtTokenUtil.getRoleTokenKey());
        // 保存到 redis
        redisUtil.set(jwtTokenUtil.getAccessTokenKey(username),accesstoken);
        redisUtil.expire(jwtTokenUtil.getAccessTokenKey(username), jwtProperties.getAccessExpiration());
        redisUtil.set(jwtTokenUtil.getRefreshTokenKey(username),refreshtoken);
        redisUtil.expire(jwtTokenUtil.getRefreshTokenKey(username),jwtProperties.getRefreshExpiration());
        redisUtil.set(jwtTokenUtil.getRoleTokenKey(username), rolestoken);
        redisUtil.expire(jwtTokenUtil.getRoleTokenKey(username), jwtProperties.getRolesExpiration());
        // 绑定到当前用户
        user.setAccessToken(accesstoken);
        user.setRefreshToken(refreshtoken);
        /**
         * 前后端分离end
         */

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}