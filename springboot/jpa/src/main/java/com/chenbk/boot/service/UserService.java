package com.chenbk.boot.service;

import com.chenbk.boot.model.User;
import com.chenbk.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kang on 2018/5/11.
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        List<User> list=new ArrayList<>();
        userRepository.findAll().forEach(x->list.add(x));
        return list;
    }

    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }

    public String postUser(@RequestBody User user) {
        userRepository.save(user);
        return "success";
    }

    @Transactional
    public String putUser(@PathVariable("id") Integer id, @RequestBody User user) {
        userRepository.update(user.getName(),user.getAge(),user.getId());
        return "success";
    }

    public String delUser(Integer id) {
        userRepository.delete(id);
        return "success";
    }


}
