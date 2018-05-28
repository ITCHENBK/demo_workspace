package com.chenbk.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Kang on 2018/5/11.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {

    public static void main(String ... args){
        SpringApplication.run(Application.class, args);
    }
}
