package com.chenbk.boot.cache;

import com.chenbk.boot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Kang on 2018/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserCache2Test {
    @Autowired
    private UserCache2 userCache2;

    @Test
    public void getUser1Test(){
        userCache2.getUser1(3);

    }

    @Test
    public void getUser2Test(){
        userCache2.getUser2(3);
    }

    @Test
    public void getUser3Test(){
        userCache2.getUser3(3);
    }

}
