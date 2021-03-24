package com.github.hollykunge.openapi.config;


import org.apache.commons.lang.StringUtils;

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