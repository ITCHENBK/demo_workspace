package com.chenbk.boot.service;

import com.chenbk.boot.cache.UserCache;
import com.chenbk.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kang on 2018/5/11.
 */
@Service
public class UserService {

    @Autowired
    private UserCache userCache;


    public List<User> getAllUser() {
        return userCache.getAllUser();
    }

    public User getUser( Integer id) {
        return userCache.getUser(id);
    }

    public String postUser( User user) {
        userCache.postUser(user);
        return "success";
    }

    public String putUser(Integer id, User user) {
        userCache.putUser(id,user);
        return "success";
    }

    public String delUser(Integer id) {
        userCache.delUser(id);
        return "success";
    }


}
