package com.chenbk.boot.mapper;

import com.chenbk.boot.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Kang on 2018/5/28.
 */
@Mapper
public interface UserMapper {

    List<User> getAllUser();

    User getUser(Integer id);

    int postUser(User user);

    int putUser(User user);

    int delUser(Integer id);
}
