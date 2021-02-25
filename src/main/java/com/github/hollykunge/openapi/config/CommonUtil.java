package com.github.hollykunge.openapi.config;

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
}