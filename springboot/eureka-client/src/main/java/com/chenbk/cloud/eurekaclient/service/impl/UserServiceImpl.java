package com.chenbk.cloud.eurekaclient.service.impl;

import com.chenbk.cloud.eurekaclient.bean.User;
import com.chenbk.cloud.eurekaclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kang on 2017/8/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into tf_f_user(name, age) values(?, ?)", name, age);
    }
    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from tf_f_user where name = ?", name);
    }
    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from tf_f_user", Integer.class);
    }
    @Override
    public void deleteAllUsers() {

        System.out.println("delete delete delete delete delete");
        jdbcTemplate.update("delete from tf_f_user");
    }

    public List<User> getAllUser() {

        return jdbcTemplate.query("select * from tf_f_user", (rs, rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setAge(rs.getInt("age"));
            user.setName(rs.getString("name"));
            return user;
        });
    }

    public User getById(Long id){
        User u=jdbcTemplate.query("select * from tf_f_user where id = ？",(rs) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setAge(rs.getInt("age"));
            user.setName(rs.getString("name"));
            return user;
        },id);
        return u;
    }

    public void deleteById(Long id){
        jdbcTemplate.update("delete from tf_f_user where id = ?", id);
    }

    public void updateById(User user){
        jdbcTemplate.update("update tf_f_user set age=? , name=? where id = ? ",user.getAge(),user.getName(),user.getId());
    }
}