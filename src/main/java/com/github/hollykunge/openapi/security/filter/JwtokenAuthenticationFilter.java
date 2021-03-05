package com.github.hollykunge.openapi.security.filter;


import com.github.hollykunge.openapi.redis.RedisUtil;
import com.github.hollykunge.openapi.security.jwt.JwtProperties;
import com.github.hollykunge.openapi.security.jwt.JwtTokenUtil;
import com.github.hollykunge.openapi.security.service.MyUserDetailsService;
import com.github.hollykunge.openapi.security.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtokenAuthenticationFilter extends OncePerRequestFilter {
    String headerName = "Authorization";
    @Autowired
    private JwtProperties jwtProperties;
    @Resource
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    RedisUtil redisUtil;


    // 将token转为用户密码的权限方式
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        /*// 取出auth
        String authHeader = request.getHeader(jwtProperties.getHeaderName());

        if (authHeader != null && authHeader.startsWith(jwtProperties.getTokenHead())) {
            // tokenBody = jwttoken
            String tokenBody = authHeader.substring(jwtProperties.getTokenHead().length());
            if (tokenBody != null) {
                // 没过期
                String username = jwtTokenUtil.getUserNameFromToken(tokenBody);
                boolean isTokenExpired = jwtTokenUtil.isTokenExpired(tokenBody);
                if (username != null && !isTokenExpired && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 根据用户名，读取权限明细
                    UserDetails userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(username);
                    if (jwtTokenUtil.isTokenSameUser(tokenBody, userDetails.getUsername())) {
                        // 生成authentication，
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);*/
        //如果不是登录
        if(!request.getRequestURI().equals("/OpenApi/auth/login")){
            // 取出auth
            String authHeader = request.getHeader(jwtProperties.getHeaderName());

            if (authHeader != null && authHeader.startsWith(jwtProperties.getTokenHead())) {
                // tokenBody
                String tokenBody = authHeader.substring(jwtProperties.getTokenHead().length());
                if (tokenBody != null) {
                    String username = jwtTokenUtil.getUserNameFromToken(tokenBody);
                    if (username != null) {
                        String accessToken = (String) redisUtil.get(jwtTokenUtil.getAccessTokenKey(username));
                        if (accessToken.equals(tokenBody)) {
                            String rolesToken = (String) redisUtil.get(jwtTokenUtil.getRoleTokenKey(username));
                            List<GrantedAuthority> authorities = null;
                            UserDetails userDetails = null;
                            if (rolesToken != null) {
                                // 缓存内有权限
                                authorities = jwtTokenUtil.getRolesFromToken(rolesToken);
                                userDetails = new MyUserDetails(username, "", "", "", false, authorities);
                            } else {
                                // 提取数据，并存入缓存
                                userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(username);
                                authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
                                //生成三个token，只用一个
                                String newRoleToken = jwtTokenUtil.generateToken(userDetails).get(jwtTokenUtil.getRoleTokenKey());
                                redisUtil.set(jwtTokenUtil.getRoleTokenKey(username),newRoleToken);
                                redisUtil.expire(jwtTokenUtil.getRoleTokenKey(username), jwtProperties.getRolesExpiration());
                            }
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, authorities);
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}