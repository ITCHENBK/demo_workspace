package com.chenbk.demo;

import com.chenbk.demo.api.UserService;
import com.chenbk.demo.model.User;

import java.util.HashMap;
import java.util.Map;

public class HelloUserServiceImpl implements UserService {

    private Map<Integer,User> users=new HashMap();

    {
        users.put(1,new User(1,"AA",18));
        users.put(2,new User(2,"BB",19));
        users.put(3,new User(3,"CC",21));
        users.put(4,new User(4,"DD",22));
    }


    @Override
    public User getUser(Integer id) {
        return users.get(id);
    }
}
