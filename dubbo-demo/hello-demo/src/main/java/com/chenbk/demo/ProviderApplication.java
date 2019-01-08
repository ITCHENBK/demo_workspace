package com.chenbk.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.chenbk.demo.api.UserService;

import java.util.concurrent.locks.LockSupport;

public class ProviderApplication {

    public static void main(String ... args){

        UserService userService=new HelloUserServiceImpl();

        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("dubbo-provider");

        RegistryConfig registryConfig=new RegistryConfig();
        registryConfig.setAddress("www.chenbk.club:2181");
        registryConfig.setProtocol("zookeeper");

        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(12346);
        protocolConfig.setThreads(50);

        ServiceConfig serviceConfig=new ServiceConfig();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();
        System.out.println("注册成功");
        LockSupport.park();
    }
}
