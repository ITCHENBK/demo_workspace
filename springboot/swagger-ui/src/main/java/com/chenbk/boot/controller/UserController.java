package com.chenbk.boot.controller;

import com.chenbk.boot.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kang on 2018/5/9.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static Map<Integer, User> map = new ConcurrentHashMap<>();
    private static List<User> list = Collections.synchronizedList(new ArrayList<User>());
    static {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setName("AA");
        map.put(1, user);
        list.add(user);

        user = new User();
        user.setId(2);
        user.setAge(28);
        user.setName("BB");
        map.put(2, user);
        list.add(user);


        user = new User();
        user.setId(3);
        user.setAge(38);
        user.setName("CC");
        map.put(3, user);
        list.add(user);
    }
    @ApiOperation(value = "获取全部用户信息", notes = "获取全部用户信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return new ArrayList<User>(map.values());
    }
    @ApiOperation(value = "根据id获取用户信息", notes = "根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Integer id) {
        return map.get(id);
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@ApiParam @RequestBody User user) {
        map.put(user.getId(), user);
        return "success";
    }
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable("id") Integer id,@ApiParam @RequestBody User user) {
        map.put(id, user);
        return "success";
    }
    @ApiOperation(value = "删除用户信息",notes = "删除用户信息")
    @ApiImplicitParam(name ="id",value = "用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delUser(Integer id) {
        map.remove(id);
        return "success";
    }
}
