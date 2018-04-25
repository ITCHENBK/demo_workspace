package com.chenbk.cloud.eurekaclient.service;

import com.chenbk.cloud.eurekaclient.bean.User;

import java.util.List;

/**
 * Created by Kang on 2017/8/9.
 */
public interface UserService {

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);
    /**
     * 根据name删除一个用户
     * @param name
     */
    void deleteByName(String name);
    /**
     * 根据ID删除用户
     * @param id 用户Id
     */
    void deleteById(Long id);
    /**
     * 根据ID获取信息
     * @param id 用户ID
     * @return
     */
    User getById(Long id);
    /**
     * 获取用户总量
     */
    Integer getAllUsers();
    /**
     * 删除所有用户
     */
    void deleteAllUsers();
    /**
     * 获取所有的用户信息
     * @return
     */
    List<User> getAllUser();

    /**
     * 更新用户信息
     * @param user
     */
    void updateById(User user);
}
