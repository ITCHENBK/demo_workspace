package com.chenbk.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by Kang on 2018/5/11.
 */

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String ... args){
        SpringApplication.run(Application.class, args);
    }
}
