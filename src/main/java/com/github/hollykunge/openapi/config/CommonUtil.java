package com.github.hollykunge.openapi.config;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author: zhuqz
 * @date: 2020/6/22 16:25
 * @description: 通用工具类
 */
public class CommonUtil {
    public static String getExceptionMessage(Throwable e){
        StringWriter sw = null;
        PrintWriter pw = null;
        try{
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }catch (Throwable ex){

        }finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    public static String getServerTime(){
        return DateUtil.now();
    }

    public static String no2EmptyStr(Object obj){
        if(obj == null){
            return "";
        }
        return obj.toString();
    }


    /**
     *
     * @param strings 验证非空变量
     * @return 是否包含空参数
     */
    public static Boolean isNullOrEmpty(String ...strings){

//        StringBuffer str = new StringBuffer();
//        for (int i = 0; i < strings.length; i++) {
//            if (StringUtils.isBlank(strings[i])){
//                str.append(i).append("、");
//            }
//        }
//        return str.toString();
       for (int i = 0; i < strings.length; i++) {
            if (StringUtils.isBlank(strings[i])){
                return true;
            }
        }
       return false;
    }
}