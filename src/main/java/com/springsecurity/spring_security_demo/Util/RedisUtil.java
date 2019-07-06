package com.springsecurity.spring_security_demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    private  RedisTemplate<String,String> redisTemplate;

    public  void save(String key,String val){
        redisTemplate.opsForValue().set(key,val);
    }

    public   <T> T get(String key){
        T val = (T)redisTemplate.opsForValue().get(key);
        return val;
    }
}
