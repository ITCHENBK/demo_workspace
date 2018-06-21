package com.chenbk.boot.service;

import com.chenbk.boot.config.Log;
import com.chenbk.boot.model.User;
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


    private static Map<Integer, User> map = new ConcurrentHashMap<>();

    static {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setName("AA");
        map.put(1, user);

        user = new User();
        user.setId(2);
        user.setAge(28);
        user.setName("BB");
        map.put(2, user);


        user = new User();
        user.setId(3);
        user.setAge(38);
        user.setName("CC");
        map.put(3, user);
    }

    @Log
    public List<User> getAllUser() {
        return new ArrayList<User>(map.values());
    }

    @Log
    public User getUser(@PathVariable("id") Integer id) {
        return map.get(id);
    }

    @Log
    public String postUser(@RequestBody User user) {
        map.put(user.getId(), user);
        return "success";
    }

    @Log
    public String putUser(@PathVariable("id") Integer id, @RequestBody User user) {
        map.put(id, user);
        return "success";
    }

    @Log
    public String delUser(Integer id) {
        map.remove(id);
        return "success";
    }


}
