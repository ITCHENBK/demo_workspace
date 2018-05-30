package com.chenbk.boot.controller;

import com.chenbk.boot.Application;
import com.chenbk.boot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Kang on 2018/5/11.
 *
 * @RunWith：这个是指定使用的单元测试执行类
 * @SpringBootTest(classes = Application.class) 指定配置类
 * @WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
 * @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)我们对数据库的操作会事务回滚
 * @Transactional
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void postUserTest( ) {
        User user=new User();
        user.setId(1);
        user.setName("ppp");
        user.setAge(99);
        System.out.println(userController.postUser(user));
    }

    @Test
    public void getAllUserTest() {
        System.out.println(userController.getAllUser());
    }

    @Test
    public void getUserTest() {
        System.out.println(userController.getUser(1));
    }
    @Test
    public void putUserTest() {
        User user=new User();
        user.setId(1);
        user.setName("QQQ");
        user.setAge(99);
        System.out.println(userController.putUser(999,user));
    }
    @Test
    public void delUserTest() {
        System.out.println(userController.delUser(1));
    }

}
