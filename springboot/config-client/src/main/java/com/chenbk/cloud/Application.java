package com.chenbk.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kang on 2018/6/28.
 */
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
@RestController
public class Application {


    @Autowired
    private ApplicationBean applicationBean;

    @RefreshScope
    @Bean
    public ApplicationBean applicationBean(ApplicationProperties applicationProperties){
        ApplicationBean applicationBean=new ApplicationBean();
        int version=applicationProperties.getVersion();
        applicationBean.setVersion(version);
        return applicationBean;
    }

    @Autowired
    private ApplicationProperties applicationProperties;

    public static void main(String ... args){
        SpringApplication.run(Application.class, args);
    }


    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){

        return applicationProperties;
    }

    @RequestMapping("/hello2")
    public String hello2(){

        return applicationBean.getVersion().toString();
    }
}
