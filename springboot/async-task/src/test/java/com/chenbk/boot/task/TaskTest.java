package com.chenbk.boot.task;

import com.chenbk.boot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.Future;

/**
 * Created by Kang on 2018/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TaskTest {
    @Autowired
    AsyncTask task;

    @Test
    public void test()throws Exception{
        task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        System.out.println(task2.get());

    }
}
