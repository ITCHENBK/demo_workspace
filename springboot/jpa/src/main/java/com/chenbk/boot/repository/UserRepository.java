package com.chenbk.boot.repository;

import com.chenbk.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Kang on 2018/5/28.
 */
public interface UserRepository extends JpaRepository<User,Integer>{

    @Modifying
    @Query("update t_user set name = ?1 ,age = ?2 where id = ?3")
    int update(String name,Integer age,Integer id);

}
