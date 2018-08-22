package com.chenbk.boot.config;

import com.chenbk.boot.lock.StandAloneLock;
import com.chenbk.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kang on 2018/5/29.
 */
@Configuration
public class RedisConfig {

    @Autowired
    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }



    @Autowired
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();
        RedisCacheManager redisCacheManager=new RedisCacheManager(redisTemplate);

        Map<String, Long> expires = new HashMap<>();
        expires.put("5m",60 * 5L);
        expires.put("1m",60 * 1L);
        expires.put("30s",30 * 1L);
        redisCacheManager.setExpires(expires);

        return redisCacheManager;
    }

    @Bean
    public StandAloneLock standAloneLock(StringRedisTemplate redisTemplate){
        return new StandAloneLock(redisTemplate);
    }

}
