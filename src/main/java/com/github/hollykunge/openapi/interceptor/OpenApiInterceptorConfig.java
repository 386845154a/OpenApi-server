package com.github.hollykunge.openapi.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: zhuqz
 * @date: 2020/6/29 17:15
 * @description: 拦截器 拦截路径
 */
@Configuration
public class OpenApiInterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired OpenApiTokenInterceptor openApiTokenInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //第三方调用接口需要token
        String tokenInterceptorPathForService = "/openApi/service";
        //自己调用发送通知需要token
        String tokenInterceptorPathForBusinessNotice = "/notice/sendNotice";
        registry.addInterceptor(openApiTokenInterceptor)
                .addPathPatterns(tokenInterceptorPathForService)
                .addPathPatterns(tokenInterceptorPathForBusinessNotice)
                .excludePathPatterns("swagger-ui.html")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/v2/api-docs-ext/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/doc.html");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

}
