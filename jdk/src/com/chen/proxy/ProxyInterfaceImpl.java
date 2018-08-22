package com.chen.proxy;

/**
 * Created by Kang on 2018/8/12.
 */
public class ProxyInterfaceImpl implements ProxyInterface {


    @Override
    public String proxyTest(String s) {
        System.out.println(s);
        return s+"  Hello world";
    }
}
