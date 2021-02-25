package com.github.hollykunge.openapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhuqz
 * @date: 2020/6/22 16:12
 * @description: template 配置
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 提供给注册中心的应用接口调用，可以客户端负载均衡
     * @param factory
     * @return
     */
    @Bean(name="balancedRestTemplate")
    public RestTemplate balancedRestTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        //处理超时时间
        int timeOut = 10;
        //连接超时时间
        int connectionOut = 20;
        //时间单位
        int unit = 1000;
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(timeOut*unit);
        factory.setConnectTimeout(connectionOut*unit);
        return factory;
    }

    /**
     * 提供给ip+端口号的接口，不能实现负载均衡
     * @param factory
     * @return
     */
    @Bean(name="restTemplate")
    RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

}
