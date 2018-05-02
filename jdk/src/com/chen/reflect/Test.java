package com.chen.reflect;

/**
 * Created by Kang on 2017/9/19.
 */
public class Test {

    public static void main(String ... args){
        Class clazz1=Object1.class;

        Class clazz2=Object2.class;
        System.out.println(clazz2.getSuperclass());
    }
}
