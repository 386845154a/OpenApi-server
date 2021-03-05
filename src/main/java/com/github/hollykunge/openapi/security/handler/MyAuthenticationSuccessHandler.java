package com.github.hollykunge.openapi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hollykunge.openapi.config.UserAuthContonstants;
import com.github.hollykunge.openapi.security.util.MyUserDetails;
import com.github.hollykunge.openapi.vo.auth.UserTokenVo;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //登录成功返回
        //jwt
        //登录成功返回
        String accessToken = ((MyUserDetails) authentication.getPrincipal()).getAccessToken();
        String refreshToken = ((MyUserDetails) authentication.getPrincipal()).getRefreshToken();
        ObjectRestResponse resultBody = new ObjectRestResponse();

        UserTokenVo userTokenVo = new UserTokenVo();
        userTokenVo.setRefreshToken(refreshToken);
        userTokenVo.setToken(accessToken);
        resultBody.data(userTokenVo);
        /*String token = ((MyUserDetails) authentication.getPrincipal()).getToken();
        ResultBody resultBody = new ResultBody("200", "登录成功："+token);*/
        //ResultBody resultBody = new ResultBody("200", "登录成功");
        //设置返回请求头
        response.setContentType("application/json;charset=utf-8");
        //写出流
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.write(mapper.writeValueAsString(resultBody));
        out.flush();
        out.close();
    }
}
