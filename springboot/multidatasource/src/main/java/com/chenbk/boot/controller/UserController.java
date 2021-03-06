package com.chenbk.boot.controller;

import com.chenbk.boot.config.DataSourceHolder;
import com.chenbk.boot.model.User;
import com.chenbk.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Kang on 2018/5/9.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUser() {
        DataSourceHolder.setDatasource("datasource1");          //选择数据源
        return userService.getAllUser();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Integer id) {
        DataSourceHolder.setDatasource("datasource2");
        return userService.getUser(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser( @RequestBody User user) {
        DataSourceHolder.setDatasource("datasource1");
        return userService.postUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable("id") Integer id, @RequestBody User user) {
        DataSourceHolder.setDatasource("datasource1");
        return userService.putUser(id,user);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delUser(Integer id) {
        DataSourceHolder.setDatasource("datasource2");
        return userService.delUser(id);
    }
}
