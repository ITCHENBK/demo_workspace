package com.chen.jmx;

/**
 * Created by Kang on 2017/8/4.
 */
public interface HelloMBean {

    public String getName();
    public void setName(String name);
    public void printHello();
    public void printHello(String whoName);
}
