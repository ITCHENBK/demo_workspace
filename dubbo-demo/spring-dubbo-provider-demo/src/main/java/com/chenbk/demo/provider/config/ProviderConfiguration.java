package com.chenbk.demo.provider.config;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.chenbk.demo.HelloUserServiceImpl;
import com.chenbk.demo.api.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfiguration {

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("dubbo-provider");
        return  applicationConfig;
    }


    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig=new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("www.chenbk.club:2181");
        return  registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setPort(25550);
        protocolConfig.setThreads(50);
        return protocolConfig;
    }

    @Bean
    public ServiceConfig<UserService> serviceServiceConfig(ApplicationConfig applicationConfig,RegistryConfig registryConfig,ProtocolConfig protocolConfig){
        ServiceConfig<UserService> serviceServiceConfig=new ServiceConfig<UserService>();
        serviceServiceConfig.setApplication(applicationConfig);
        serviceServiceConfig.setRegistry(registryConfig);
        serviceServiceConfig.setProtocol(protocolConfig);
        serviceServiceConfig.setVersion("1.0.0");
        serviceServiceConfig.setInterface(UserService.class);
        serviceServiceConfig.setRef(new HelloUserServiceImpl());
        return serviceServiceConfig;
    }

}
