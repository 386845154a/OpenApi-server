package com.github.hollykunge.openapi.interceptor;

/**
 * @author: zhuqz
 * @date: 2020/7/1 13:28
 * @description: 存储线程本地变量
 */
public class RequestThreadLocal {
    private static ThreadLocal<String> appIdLocal = new ThreadLocal<>();
    private RequestThreadLocal(){

    }
    public static String getApp(){
       return appIdLocal.get();
    }
    public static void setApp(String appId){
        appIdLocal.set(appId);
    }
}