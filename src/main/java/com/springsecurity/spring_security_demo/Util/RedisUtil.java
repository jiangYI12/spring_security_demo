package com.springsecurity.spring_security_demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
    @Autowired
    private static RedisTemplate<String,Object> redisTemplate;

    public static void save(String key,Object val){
        redisTemplate.opsForValue().set(key,val);
    }

    public static <T> T get(String key){
        T val = (T)redisTemplate.opsForValue().get(key);
        return val;
    }
}
