package com.chenbk.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by Kang on 2018/6/28.
 */
@EnableConfigServer
@SpringBootApplication
public class Application {

    public static void main(String ... args){
        SpringApplication.run(Application.class, args);
    }
}
