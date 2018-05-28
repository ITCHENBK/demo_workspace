package com.chenbk.boot.dao;

import com.chenbk.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kang on 2018/5/11.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<User> getAllUser() {
        String sql="select * from t_user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User getUser(@PathVariable("id") Integer id) {
        String sql="select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new UserRowMapper());
    }

    public void postUser(@RequestBody User user) {
        String sql="insert into t_user(id,name,age) values(?,?,?)";
        jdbcTemplate.update(sql,user.getId(),user.getName(),user.getAge());
    }

    public void putUser(@PathVariable("id") Integer id, @RequestBody User user) {
        String sql="update t_user set name = ?,age = ? where id = ?";
        jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getId());
    }

    public void delUser(Integer id) {
       String sql ="del from t_user where id = ?";
        jdbcTemplate.update(sql,id);
    }

    class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user=new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            return user;
        }
    }
}
