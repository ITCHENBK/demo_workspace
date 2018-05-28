package com.chenbk.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenbk on 2017/9/20.
 */
@ConfigurationProperties(prefix = "database")
public class DataSourceProp {
    private List<Map<String, Object>> dataSource = new ArrayList<>();

    public List<Map<String, Object>> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Map<String, Object>> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return "DataSourceProp{" +
                "dataSource=" + dataSource +
                '}';
    }
}
