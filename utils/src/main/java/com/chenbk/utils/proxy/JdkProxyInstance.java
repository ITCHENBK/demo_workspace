package com.chenbk.utils.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by Kang on 2018/8/12.
 */
public class JdkProxyInstance {

    /**
     *
     * @param proxyObject 被代理类
     * @param proxyInterface  代理接口
     * @return
     */
    public static   Object instance(Object proxyObject,Class ... proxyInterface){
       return Proxy.newProxyInstance(JdkProxyInstance.class.getClassLoader(),proxyInterface,new TestInvocationHandler(proxyObject));
    }
}
