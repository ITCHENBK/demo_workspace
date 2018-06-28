package com.chenbk.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by Kang on 2017/9/20.
 */
@Configuration
@EnableConfigurationProperties(DataSourceProp.class)
public class DataSourceConfig {

    @Autowired
    private DataSourceProp dataSourceProp;

    @Bean
    public DataSource multiDataSource(){
        return new MultiDataSource(dataSourceProp);
    }
}
