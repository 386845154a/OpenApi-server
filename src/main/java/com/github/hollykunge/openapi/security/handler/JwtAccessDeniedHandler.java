package com.github.hollykunge.openapi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hollykunge.openapi.config.UserAuthContonstants;
import com.github.hollykunge.openapi.vo.base.ResVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zhuqz
 * @date: 2021/3/8 13:58
 * @description:
 */
@Component
public class JwtAccessDeniedHandler  implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //登录失败信息返回
        ResVo resultBody = new ResVo();
        resultBody.setCode(UserAuthContonstants.TOKEN_AUTHORITY_NO_CODE);
        resultBody.setMsg(UserAuthContonstants.TOKEN_AUTHORITY_NO_TIP+e.getLocalizedMessage());
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

