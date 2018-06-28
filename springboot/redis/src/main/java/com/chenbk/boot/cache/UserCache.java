package com.chenbk.boot.cache;

import com.chenbk.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Kang on 2018/5/29.
 */
@Component
public class UserCache {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public List<User> getAllUser(){
        Set<String> keys = redisTemplate.keys("*");
        List<User> users=new ArrayList<>();
        for(String key:keys){
          users.add(redisTemplate.opsForValue().get(key));
        }
        return users;
    }

    public User getUser( Integer id) {
        return redisTemplate.opsForValue().get(id+"");
    }

    public void postUser( User user) {
        redisTemplate.opsForValue().set(user.getId()+"",user);
    }

    public void putUser(Integer id, User user) {
        redisTemplate.opsForValue().set(id+"",user);
    }

    public void delUser(Integer id) {
        redisTemplate.delete(id+"");
    }


    /**
     * 清理缓存
     */
    public void clearCache() {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "success";
            }
        });
        if ("success".equals(result)) {

        }
    }
}
