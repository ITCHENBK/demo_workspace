package com.chenbk.boot.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kang on 2018/5/11.
 */
public class MultiDataSource extends AbstractRoutingDataSource {

    private DataSourceProp dataSourceProp;

    private Map<Object, Object> dataSources = new HashMap<>();

    public MultiDataSource(DataSourceProp dataSourceProp) {
        this.dataSourceProp = dataSourceProp;
        init();
    }

    private void init() {
        for (Map<String, Object> tmp : dataSourceProp.getDataSource()) {
            String url = tmp.get("url").toString();
            String username = tmp.get("username").toString();
            String password = tmp.get("password").toString();
            String uniqueResourceName = tmp.get("uniqueResourceName").toString();
            String driverClassName = tmp.get("driverClassName").toString();
            DataSource dataSource = DataSourceBuilder.create().
                    driverClassName(driverClassName).
                    url(url).username(username).
                    password(password).build();
            dataSources.put(uniqueResourceName,dataSource);

        }
        DataSourceHolder.setDatasource("datasource1");
        setTargetDataSources(dataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDatasource();
    }
}
