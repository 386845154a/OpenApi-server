package com.github.hollykunge.openapi.controller.auth;

/**
 * @author: zhuqz
 * @date: 2021/3/5 13:38
 * @description:
 */

import com.github.hollykunge.openapi.config.UserAuthContonstants;
import com.github.hollykunge.openapi.redis.RedisUtil;
import com.github.hollykunge.openapi.security.jwt.JwtProperties;
import com.github.hollykunge.openapi.security.jwt.JwtTokenUtil;
import com.github.hollykunge.openapi.security.param.LoginParam;
import com.github.hollykunge.openapi.security.param.RefreshTokenParam;
import com.github.hollykunge.openapi.security.param.TokenParam;
import com.github.hollykunge.openapi.security.service.MyUserDetailsService;
import com.github.hollykunge.openapi.security.util.MyUserDetails;
import com.github.hollykunge.openapi.vo.res.LoginSuccessTokenVo;
import com.github.hollykunge.openapi.vo.res.RefreshUesrTokenVo;
import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zhuqz
 * @date: 2021/3/4 16:01
 * @description:
 */
@RestController
@RequestMapping("userAuth")
public class UserAuthController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * rest登录 不使用form登录
     * @param param
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<LoginSuccessTokenVo> login(@RequestBody LoginParam param){
        String userName = param.getUserName();
        String pwd = param.getPwd();
        //根据输入的用户密码，读取数据库中信息
        MyUserDetails user = (MyUserDetails) myUserDetailsService.loadUserByUsername(userName);
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
        if (!passwordEncoder.matches(pwd, user.getPassword())) {
            throw new BadCredentialsException("PASSWORD INVALID!");
        }
        // 生成新token
        Map<String,String> tokens = jwtTokenUtil.generateToken(user);
        String accesstoken = tokens.get(jwtTokenUtil.getAccessTokenKey());
        String refreshtoken = tokens.get(jwtTokenUtil.getRefreshTokenKey());
        String rolestoken = tokens.get(jwtTokenUtil.getRoleTokenKey());
        // 保存到 redis
        redisUtil.set(jwtTokenUtil.getAccessTokenKey(userName),accesstoken);
        redisUtil.expire(jwtTokenUtil.getAccessTokenKey(userName), jwtProperties.getAccessExpiration());
        redisUtil.set(jwtTokenUtil.getRefreshTokenKey(userName),refreshtoken);
        redisUtil.expire(jwtTokenUtil.getRefreshTokenKey(userName),jwtProperties.getRefreshExpiration());
        redisUtil.set(jwtTokenUtil.getRoleTokenKey(userName), rolestoken);
        redisUtil.expire(jwtTokenUtil.getRoleTokenKey(userName), jwtProperties.getRolesExpiration());
        LoginSuccessTokenVo loginSuccessTokenVo = new LoginSuccessTokenVo();
        loginSuccessTokenVo.setCode("200");
        loginSuccessTokenVo.setToken(accesstoken);
        loginSuccessTokenVo.setRefreshToken(refreshtoken);
        return new ObjectRestResponse<>().rel(true).data(loginSuccessTokenVo);
    }
    /**
     * 刷新token
     * @param refreshTokenParam
     * @return
     */
    @RequestMapping(value = "/token/refresh", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse refreshToken(@RequestBody RefreshTokenParam refreshTokenParam) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        RefreshUesrTokenVo res = new RefreshUesrTokenVo();
        if (refreshTokenParam == null || refreshTokenParam.getRefreshToken()==null || "".equals(refreshTokenParam.getRefreshToken())) {
            objectRestResponse.rel(false);
            res.setCode(UserAuthContonstants.ERROR_CODE);
            res.setMsg(UserAuthContonstants.REFRESHTOKEN_EMPTY_TIP);
            objectRestResponse.data(res);
            return objectRestResponse;
        }
        String refreshToken = refreshTokenParam.getRefreshToken();
        refreshToken = refreshToken.substring(jwtProperties.getTokenHead().length());
        if(jwtTokenUtil.isTokenExpired(refreshToken)){
            objectRestResponse.rel(false);
            res.setCode(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_ERROR_CODE);
            res.setMsg(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_ERROR_TIP);
            objectRestResponse.data(res);
            return objectRestResponse;
        }

        String username = jwtTokenUtil.getUserNameFromToken(refreshToken);

        if (username == null) {
            objectRestResponse.rel(false);
            res.setCode(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_FORMAT_ERROR_CODE);
            res.setMsg(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_FORMAT_ERROR_TIP);
            objectRestResponse.rel(false);
            return objectRestResponse;
        }
        Object refreshtokenObj = redisUtil.get(jwtTokenUtil.getRefreshTokenKey(username));
        if(refreshtokenObj == null){
            objectRestResponse.rel(false);
            res.setCode(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_ERROR_CODE);
            res.setMsg(UserAuthContonstants.REFRESHTOKEN_OVERDATE_RELOGIN_TIP);
            objectRestResponse.data(res);
            return objectRestResponse;
        }
        if(!refreshtokenObj.toString().equals(refreshToken)){
            objectRestResponse.rel(false);
            res.setCode(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_FORMAT_ERROR_CODE);
            res.setMsg(UserAuthContonstants.REFRESHTOKEN_AUTHORITY_FORMAT_ERROR_TIP);
            objectRestResponse.data(res);
            return objectRestResponse;
        }

        Object accesstokenObj = redisUtil.get(jwtTokenUtil.getAccessTokenKey(username));
        String accesstoken = accesstokenObj==null?"":(String)accesstokenObj;
        String new_accesstoken = jwtTokenUtil.refreshHeadToken(refreshToken, accesstoken);
        if (new_accesstoken == null) {
            res.setCode(UserAuthContonstants.TOKEN_AUTHORITY_FORMAT_ERROR_CODE);
            res.setMsg(UserAuthContonstants.TOKEN_AUTHORITY_FORMAT_ERROR_TIP);
            objectRestResponse.data(res);
            return objectRestResponse;
        }
        if (new_accesstoken == "") {
            objectRestResponse.rel(false);
            res.setMsg(UserAuthContonstants.TOKEN_REFRESH_TOO_TIMES_TIP);
            res.setToken(accesstoken);
            objectRestResponse.data(res);
            return objectRestResponse;
        }
        redisUtil.set(jwtTokenUtil.getAccessTokenKey(username), new_accesstoken);
        res.setToken(new_accesstoken);
        objectRestResponse.data(res);
        return objectRestResponse;
    }

    /**
     * 清空令牌
     * @param tokenParam
     * @return
     */
    @RequestMapping(value = "/token/clear", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse logout(@RequestBody TokenParam tokenParam) {
        ResVo resVo = new ResVo();
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        if (tokenParam == null || tokenParam.getToken()==null || "".equals(tokenParam.getToken())) {
            resVo.setCode(UserAuthContonstants.TOKEN_NONE_CODE);
            resVo.setMsg(UserAuthContonstants.TOKEN_NONE_TIP);
            objectRestResponse.rel(false);
            objectRestResponse.data(resVo);
            return objectRestResponse;
        }
        String token = tokenParam.getToken();
        token = token.substring(jwtProperties.getTokenHead().length());
        String username = jwtTokenUtil.getUserNameFromToken(token);
        if (username == null) {
            resVo.setCode(UserAuthContonstants.TOKEN_AUTHORITY_FORMAT_ERROR_CODE);
            resVo.setMsg(UserAuthContonstants.TOKEN_AUTHORITY_FORMAT_ERROR_TIP);
            objectRestResponse.rel(false);
            objectRestResponse.data(resVo);
            return objectRestResponse;
        }

        Object tokenObj = redisUtil.get(jwtTokenUtil.getAccessTokenKey(username));
        if(tokenObj != null){
            if(!tokenObj.toString().equals(token)){
                resVo.setCode(UserAuthContonstants.TOKEN_AUTHORITY_FORMAT_ERROR_CODE);
                resVo.setMsg(UserAuthContonstants.TOKEN_AUTHORITY_FORMAT_ERROR_TIP);
                objectRestResponse.rel(false);
                objectRestResponse.data(resVo);
                return objectRestResponse;
            }
        }
        redisUtil.expire(jwtTokenUtil.getAccessTokenKey(username), 1);
        redisUtil.expire(jwtTokenUtil.getRefreshTokenKey(username), 1);
        redisUtil.expire(jwtTokenUtil.getRoleTokenKey(username), 1);
        objectRestResponse.rel(false);
        objectRestResponse.data(resVo);
        return objectRestResponse;
    }
}