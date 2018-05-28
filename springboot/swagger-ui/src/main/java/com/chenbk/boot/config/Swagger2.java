package com.chenbk.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by Kang on 2018/5/9.
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chenbk.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建页面的标题信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2小demo")
                .description("Spring Boot中使用Swagger2小demo")
                .termsOfServiceUrl("https://my.oschina.net/chenbkit")
                .contact("chenbk")
                .version("1.0")
                .build();
    }
}
