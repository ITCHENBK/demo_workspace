package com.chenbk.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Kang on 2018/5/11.
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String ... args){
        SpringApplication.run(Application.class, args);
    }
}
