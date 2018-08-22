package com.chen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Kang on 2018/8/12.
 */
public class JdkProxyInstance {

     static  class TestInvocationHandler implements InvocationHandler{

        private  Object proxyObject;

        public TestInvocationHandler(Object proxyObject) {
            this.proxyObject = proxyObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before method");
            Object o=method.invoke(proxyObject,args);
            System.out.println("after method");
            return o;
        }
    }

    public static   Object instance(Object proxyObject,Class ... proxyInterface){
       return Proxy.newProxyInstance(JdkProxyInstance.class.getClassLoader(),proxyInterface,new TestInvocationHandler(proxyObject));
    }
}
