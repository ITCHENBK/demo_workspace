package com.chen.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Kang on 2018/8/12.
 */
public class CglibProxyInstance {

    static class TestMethodInterceptor implements MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("beford method");
            Object object=methodProxy.invokeSuper(o,objects);
            System.out.println("after method");
            return object;
        }
    }

    public static Object instance(Class clazz){
        Enhancer enhancer=new Enhancer();
        enhancer.setCallback(new TestMethodInterceptor());
        enhancer.setSuperclass(clazz);
        return enhancer.create();
    }
}
