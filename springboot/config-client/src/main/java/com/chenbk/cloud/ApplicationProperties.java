package com.chenbk.cloud;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Kang on 2018/11/26.
 */
@ConfigurationProperties(prefix = "")
public class ApplicationProperties {

    private String name;

    private Integer version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
