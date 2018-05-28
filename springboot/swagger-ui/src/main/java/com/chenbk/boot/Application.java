package com.chenbk.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Kang on 2018/5/9.
 */
@EnableSwagger2
@SpringBootApplication
public class Application {

    public static void main(String ... args){
        SpringApplication.run(Application.class, args);
    }
}
