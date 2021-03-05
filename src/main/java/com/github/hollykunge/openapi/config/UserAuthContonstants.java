package com.github.hollykunge.openapi.config;

/**
 * @author: zhuqz
 * @date: 2021/3/5 14:09
 * @description:
 */
public class UserAuthContonstants {
    public final  static String SUCCESS_CODE = "200";
    public final  static String SUCCESS_TIP = "操作成功";
    public final  static String ERROR_CODE = "-1";
    public final  static String ERROR_TIP = "操作失败";
    public final  static String TOKEN_AUTHORITY_ERROR_CODE = "100401";
    public final  static String TOKEN_AUTHORITY_ERROR_TIP = "token失效";
    public final  static String REFRESHTOKEN_AUTHORITY_ERROR_CODE = "100402";
    public final  static String REFRESHTOKEN_AUTHORITY_ERROR_TIP  = "refreshToken失效";
    public final  static String TOKEN_AUTHORITY_FORMAT_ERROR_CODE = "100403";
    public final  static String TOKEN_AUTHORITY_FORMAT_ERROR_TIP = "token格式错误";
    public final  static String REFRESHTOKEN_AUTHORITY_FORMAT_ERROR_CODE = "100404";
    public final  static String REFRESHTOKEN_AUTHORITY_FORMAT_ERROR_TIP  = "refreshToken格式错误";
    public final  static String TOKEN_NONE_CODE = "100405";
    public final  static String TOKEN_NONE_TIP = "没有token";

    public final  static String REFRESHTOKEN_EMPTY_TIP  = "refreshToken不能为空";
    public final  static String REFRESHTOKEN_OVERDATE_RELOGIN_TIP  = "刷新令牌过期，请重新登录";
    public final  static String TOKEN_REFRESH_TOO_TIMES_TIP  = "令牌不要频繁刷新";




}