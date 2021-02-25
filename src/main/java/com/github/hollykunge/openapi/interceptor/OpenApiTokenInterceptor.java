package com.github.hollykunge.openapi.interceptor;

import cn.hutool.json.JSONUtil;
import com.github.hollykunge.openapi.auth.ApiToken;
import com.github.hollykunge.openapi.biz.AppBiz;
import com.github.hollykunge.openapi.biz.ApplyBiz;
import com.github.hollykunge.openapi.biz.ServiceBiz;
import com.github.hollykunge.openapi.config.CommonUtil;
import com.github.hollykunge.openapi.config.ConfigConstants;
import com.github.hollykunge.openapi.vo.auth.TokenResVo;
import com.github.hollykunge.openapi.vo.param.OpenApiRequestParamVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zhuqz
 * @date: 2020/6/29 16:48
 * @description: token拦截器
 */
@Component
public class OpenApiTokenInterceptor  implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(OpenApiTokenInterceptor.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AppBiz appBiz;
    @Autowired
    private ApplyBiz applyBiz;
    @Autowired
    private ServiceBiz serviceBiz;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验token是否存在
        String accessToken = request.getHeader(ConfigConstants.API_TOKEN_HEADER);
        TokenResVo tokenResVo;
        if(accessToken == null || "".equals(accessToken)){
            tokenResVo = new TokenResVo();
            tokenResVo.setCode(ConfigConstants.RES_ERROR_NO_TOKEN);
            tokenResVo.setMsg(ConfigConstants.RES_ERROR_NO_TOKEN_MSG);
            writeResponseBackInf(response,tokenResVo);
            return false;
        }
        //校验token是否过期
        ApiToken apiToken = (ApiToken)redisTemplate.opsForValue().get(ConfigConstants.RESDIS_TOKEN_COLLECTION+accessToken);
        if(apiToken == null){
            tokenResVo = new TokenResVo();
            tokenResVo.setCode(ConfigConstants.RES_ERROR_TOKEN_EXPIRE);
            tokenResVo.setMsg(ConfigConstants.RES_ERROR_TOKEN_EXPIRE_MSG);
            writeResponseBackInf(response,tokenResVo);
            return false;
        }
        //设置线程变量appId
        RequestThreadLocal.setApp(apiToken.getAppId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       //清空本地线程数据，防止泄露内存
        RequestThreadLocal.setApp(null);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    private void writeResponseBackInf(HttpServletResponse httpResponse , TokenResVo tokenResVo){
        PrintWriter pw = null;
        httpResponse.setCharacterEncoding(ConfigConstants.DEFAULT_ENCODE);
        String contentType = "application/json; charset=utf-8";
        httpResponse.setContentType(contentType);
        try {
            pw = httpResponse.getWriter();
            ObjectRestResponse<TokenResVo> resObj = new ObjectRestResponse<>();
            resObj.data(tokenResVo).rel(false);
            pw.print(JSONUtil.parse(resObj));
        } catch (IOException e) {
            String errorInf = "openApi 信息反馈错误";
            logger.error(errorInf);
            logger.error(CommonUtil.getExceptionMessage(e));
        } finally {
            if(pw!=null){
                pw.close();
            }
        }
    }

    /**
     * 读取body参数
     * @param body
     */
    public OpenApiRequestParamVo readRequestBody(String body)
    {
        try
        {
            OpenApiRequestParamVo param = JSONUtil.toBean(body,OpenApiRequestParamVo.class);
            return param;
        }
        catch (Exception e)
        {
            String msg = "openApi 拦截器获取参数报错";
            logger.error(msg);
            logger.error(CommonUtil.getExceptionMessage(e));
        }
        return null;
    }
}