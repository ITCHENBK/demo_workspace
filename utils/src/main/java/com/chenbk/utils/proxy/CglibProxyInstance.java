package com.chenbk.utils.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Kang on 2018/8/12.
 */
public class CglibProxyInstance {

    public static Object instance(Class clazz){
        Enhancer enhancer=new Enhancer();
        enhancer.setCallback(new TestMethodInterceptor());
        enhancer.setSuperclass(clazz);
        return enhancer.create();
    }
}
