package com.conmu.utils;


import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口限流工具
 *
 * @author mucongcong
 * @date 2022/07/14 11:40
 * @since
 **/
@Component
public class TpsUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String tpsLuaScript = "local key = KEYS[1]\n" +
            "local capacity = tonumber(ARGV[2])\n" +
            "local timestamp = tonumber(ARGV[3])\n" +
            "local id = KEYS[2]\n" +
            "local count = redis.call(\"zcard\", key)\n" +
            "local allowed = 0\n" +
            "if count < capacity then\n" +
            "  redis.call(\"zadd\", key, timestamp, id)\n" +
            "  allowed = 1\n" +
            "  count = count + 1\n" +
            "end\n" +
            "-- redis.call(\"setex\", key, timestamp)\n" +
            "return { allowed, count }";


    public Object getTpsPermission(String key1, String key2, int capacity, int time) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key1);
        keys.add(key2);
        values.add(String.valueOf(capacity));
        values.add(String.valueOf(time));
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(tpsLuaScript, Long.class);

        Object result = redisTemplate.execute(redisScript, keys, capacity, time);
        return result;
    }




    private static class TpsParams{
        private boolean allowed;

        private int count;

        public boolean isAllowed() {
            return allowed;
        }

        public void setAllowed(boolean allowed) {
            this.allowed = allowed;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


}
