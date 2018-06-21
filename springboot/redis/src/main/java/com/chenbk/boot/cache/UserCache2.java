package com.chenbk.boot.cache;

import com.chenbk.boot.dao.UserDao;
import com.chenbk.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by Kang on 2018/6/13.
 */
@Component
public class UserCache2 {



    @Autowired
    private UserDao userDao;

    @Cacheable(cacheNames = "30s",key = "'getUser1'+#id")
    public User getUser1(Integer id) {
        System.out.println("读缓存失败  getUser1");
        return userDao.getUser(id);
    }

    @Cacheable(cacheNames = "1m",key = "'getUser2'+#id")
    public User getUser2(Integer id) {
        System.out.println("读缓存失败  getUser2");
        return userDao.getUser(id);
    }

    @Cacheable(cacheNames = "5m",key = "'getUser3'+#id")
    public User getUser3(Integer id) {
        System.out.println("读缓存失败  getUser3");
        return userDao.getUser(id);
    }


}
