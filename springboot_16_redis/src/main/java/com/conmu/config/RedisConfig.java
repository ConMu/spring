package com.conmu.config;

/**
 * 核心配置就是配置 值的序列化方法
 *
 * @author mucongcong
 * @date 2022/05/20 14:24
 * @since
 **/


import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        FastJsonRedisSerializer<Object> objectFastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //      自定义的RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置key的序列化方法
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 核心的设置   1.2.36版本自动提供
        redisTemplate.setValueSerializer(objectFastJsonRedisSerializer);
        //        对hash的序列化操作设置
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(objectFastJsonRedisSerializer);
        //        注册到工程类
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}



