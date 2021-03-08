package com.github.hollykunge.openapi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hollykunge.openapi.config.UserAuthContonstants;
import com.github.hollykunge.openapi.vo.base.ResVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        //登录失败信息返回
        ResVo resultBody = new ResVo();
        resultBody.setCode(UserAuthContonstants.TOKEN_NOTEXIST_OR_OVERDATE_CODE);
        resultBody.setMsg(UserAuthContonstants.TOKEN_NOTEXIST_OR_OVERDATE_TIP+e.getLocalizedMessage());
        //设置返回请求头
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //写出流
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.write(mapper.writeValueAsString(resultBody));
        out.flush();
        out.close();
    }
}
