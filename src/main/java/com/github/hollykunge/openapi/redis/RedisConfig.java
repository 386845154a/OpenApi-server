package com.github.hollykunge.openapi.redis;

import io.lettuce.core.internal.LettuceFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author: zhuqz
 * @date: 2020/6/29 10:59
 * @description:
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory){
       RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();
       redisTemplate.setKeySerializer(new StringRedisSerializer());
       redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
       redisTemplate.setConnectionFactory(redisConnectionFactory);
       return redisTemplate;
    }
}