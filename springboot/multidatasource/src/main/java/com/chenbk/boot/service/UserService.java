package com.chenbk.boot.service;

import com.chenbk.boot.dao.UserDao;
import com.chenbk.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kang on 2018/5/11.
 */
@Service
public class UserService {


    @Autowired
    private UserDao userDao;

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User getUser(@PathVariable("id") Integer id) {
        return userDao.getUser(id);
    }

    public String postUser(@RequestBody User user) {
        userDao.postUser(user);
        return "success";
    }

    public String putUser(@PathVariable("id") Integer id, @RequestBody User user) {
        userDao.putUser(id,user);
        return "success";
    }

    public String delUser(Integer id) {
        userDao.delUser(id);
        return "success";
    }


}
