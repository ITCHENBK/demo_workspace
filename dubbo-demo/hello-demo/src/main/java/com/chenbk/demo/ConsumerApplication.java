package com.chenbk.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.chenbk.demo.api.UserService;

public class ConsumerApplication {

    public static void main(String ... args){

        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("dubbo-consumer");

        RegistryConfig registryConfig=new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("www.chenbk.club:2181");

        ReferenceConfig<UserService> referenceConfig=new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setVersion("1.0.0");

        UserService userService=referenceConfig.get();

        System.out.println(userService.getUser(1));

    }

}
