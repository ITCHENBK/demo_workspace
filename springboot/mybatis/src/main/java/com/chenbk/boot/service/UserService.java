package com.chenbk.boot.service;

import com.chenbk.boot.mapper.UserMapper;
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
    private UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public User getUser( Integer id) {
        return userMapper.getUser(id);
    }

    public String postUser( User user) {
        userMapper.postUser(user);
        return "success";
    }

    public String putUser(Integer id, User user) {
        user.setId(id);
        userMapper.putUser(user);
        return "success";
    }

    public String delUser(Integer id) {
        userMapper.delUser(id);
        return "success";
    }


}
