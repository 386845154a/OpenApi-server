package com.github.hollykunge.openapi.config;

/**
 * @author: zhuqz
 * @date: 2020/6/22 09:13
 * @description: 常量
 */
public class ConfigConstants {
    /**
     * 操作结果成功
     */
    public static final String RES_SUCCESS = "1";
    /**
     * 操作结果成功
     */
    public  static final String RES_SUCCESS_MSG = "操作成功";
    /**
     * 操作结果失败 系统出错
     */
    public static  final String RES_ERROR_SYSTEM = "0";
    /**
     * 操作结果失败
     */
    public static  final String RES_FAIL_MSG = "操作失败";
    /**
     * 操作结果失败 应用不存在
     */
    public static  final String RES_ERROR_NOT_EXISTS_APP = "2";
    /**
     * 操作结果失败 应用不存在或者不可用 MSG
     */
    public static  final String RES_ERROR_NOT_EXISTS_APP_MSG = "应用不存在或者不可用";
    /**
     * 操作结果失败 接口不存在
     */
    public static  final String RES_ERROR_NOT_EXISTS_API = "3";
    /**
     * 操作结果失败 接口不存在或者不可用 文本
     */
    public static  final String RES_ERROR_NOT_EXISTS_API_MSG = "接口不存在或者不可用";
    /**
     * 操作结果失败 无权限
     */
    public static  final String RES_ERROR_AUTHORITY = "4";
    /**
     * 操作结果失败 无使用openApi权限
     */
    public static  final String RES_ERROR_AUTHORITY_MSG = "无使用openApi权限";
    /**
     * 操作结果失败 请求类型未知
     */
    public static  final String RES_ERROR_UNKNOWN_REQUEST_TYPE = "5";
    /**
     * 操作结果失败 请求类型未知
     */
    public static  final String RES_ERROR_UNKNOWN_REQUEST_TYPE_MSG = "请求类型未知";

    /**
     * 操作结果失败 注册应用名字已经存在
     */
    public static  final String RES_ERROR_REGISTER_NAME_EXIST = "6";
    /**
     * 操作结果失败 注册应用名字已经存在 文本
     */
    public static  final String RES_ERROR_REGISTER_NAME_EXIST_MSG = "注册的app名称已经存在";
    /**
     * 操作结果失败 注册应用信息不全
     */
    public static  final String RES_ERROR_REGISTER_APP_NULL = "7";
    /**
     * 操作结果失败 注册应用信息不全 文本
     */
    public static  final String RES_ERROR_REGISTER_APP_NULL_MSG = "注册的app信息不全";
    /**
     * 操作结果失败 获取token信息不全
     */
    public static  final String RES_ERROR_GET_TOKEN_APP_NULL = "8";
    /**
     * 操作结果失败 注册应用信息不全 文本
     */
    public static  final String RES_ERROR_GET_TOKEN_APP_NULL_MSG = "获取apiToken的信息不全";
    /**
     * 操作结果失败 申请服务api的信息不全
     */
    public static  final String RES_ERROR_APPLY_NULL = "9";
    /**
     * 操作结果失败 申请的服务api信息不全 文本
     */
    public static  final String RES_ERROR_APPLY_NULL_MSG = "申请的服务api信息不全";
    /**
     * 操作结果失败 获取token的app秘钥错误
     */
    public static  final String RES_ERROR_GET_TOKEN_APP_SECRET = "10";
    /**
     * 操作结果失败 获取token的app秘钥错误 文本
     */
    public static  final String RES_ERROR_GET_TOKEN_APP_SECRET_MSG = "获取token的app秘钥错误";
    /**
     * 操作结果失败 没有token
     */
    public static  final String RES_ERROR_NO_TOKEN = "11";
    /**
     * 操作结果失败  没有token 文本
     */
    public static  final String RES_ERROR_NO_TOKEN_MSG = "请求无openApi的token";
    /**
     * 操作结果失败 token过期
     */
    public static  final String RES_ERROR_TOKEN_EXPIRE = "12";
    /**
     * 操作结果失败  token过期 文本
     */
    public static  final String RES_ERROR_TOKEN_EXPIRE_MSG = "token无效或者已经过期";
    /**
     * 操作结果失败 请求参数格式错误
     */
    public static  final String RES_ERROR_REQUEST_PARAM_FORMAT = "13";
    /**
     * 操作结果失败  请求参数格式错误 文本
     */
    public static  final String RES_ERROR_REQUEST_PARAM_FORMAT_MSG = "请求参数格式错误";
    /**
     * 操作结果失败 注册服务api信息不全
     */
    public static  final String RES_ERROR_REGISTER_SERVICE_NULL = "14";
    /**
     * 操作结果失败 注册服务api信息不全 文本
     */
    public static  final String RES_ERROR_REGISTER_SERVICE_NULL_MSG = "注册的服务api信息不全";
    /**
     * 操作结果失败 注册的服务api已经存在
     */
    public static  final String RES_ERROR_REGISTER_API_EXIST = "15";
    /**
     * 操作结果失败 注册的服务api已经存在 文本
     */
    public static  final String RES_ERROR_REGISTER_API_EXIST_MSG = "注册的服务api已经存在";
    /**
     * 操作结果失败 申请服务api已经存在
     */
    public static  final String RES_ERROR_APPLY_API_EXIST = "16";
    /**
     * 操作结果失败 申请服务api已经存在 文本
     */
    public static  final String RES_ERROR_APPLY_API_EXIST_MSG = "申请服务api已经存在";
    /**
     * 操作结果失败 服务降级提示
     */
    public static  final String RES_ERROR_API_FALLBACK = "17";
    /**
     * 操作结果失败 服务降级提示 文本
     */
    public static  final String RES_ERROR_API_FALLBACK_MSG = "系统正忙清稍后再试";
    /**
     * 操作结果失败 注册应用主页已经存在
     */
    public static  final String RES_ERROR_REGISTER_MAIN_URL_EXIST = "18";
    /**
     * 操作结果失败 注册应用主页已经存在 文本
     */
    public static  final String RES_ERROR_REGISTER_MAIN_URL_EXIST_MSG = "注册的app主页已经存在";
    /**
     * 请求类型get
     */
    public static  final String REQUEST_TYPE_GET = "get";
    /**
     * 请求类型post
     */
    public static  final String REQUEST_TYPE_POST = "post";
    /**
     * 请求类型delete
     */
    public static  final String REQUEST_TYPE_DELETE = "delete";
    /**
     * 请求类型get
     */
    public static  final String REQUEST_TYPE_PUT = "put";
    /**
     * redis token 集合
     */
    public static  final String RESDIS_TOKEN_COLLECTION = "openapi:token:";
    /**
     * token 请求头
     */
    public static  final String API_TOKEN_HEADER = "open_api_token";
    /**
     * 默认编码格式
     */
    public static  final String DEFAULT_ENCODE = "utf-8";
}