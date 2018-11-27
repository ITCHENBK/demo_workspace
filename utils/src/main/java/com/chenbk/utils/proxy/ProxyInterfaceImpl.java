package com.chenbk.utils.proxy;

import java.sql.SQLException;

/**
 * Created by Kang on 2018/8/12.
 */
public class ProxyInterfaceImpl implements ProxyInterface {
    @Override
    public String proxyTest(String s) throws SQLException {
//        System.out.println(s);
//        return s+"  Hello world";

        throw new SQLException("123");
    }
}
