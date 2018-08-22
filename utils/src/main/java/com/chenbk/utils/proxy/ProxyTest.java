package com.chenbk.utils.proxy;

import org.junit.Test;

/**
 * Created by Kang on 2018/8/12.
 */
public class ProxyTest {

    @Test
    public void jdkProxyTest(){
        ProxyInterface proxyInterface=new ProxyInterfaceImpl();

        proxyInterface= (ProxyInterface)JdkProxyInstance.instance(proxyInterface,ProxyInterface.class);

        String result=proxyInterface.proxyTest("chenbk");

        System.out.println(result);

    }


    @Test
    public void cglibProxyTest(){
        ProxyInterfaceImpl proxyInterface  =(ProxyInterfaceImpl)CglibProxyInstance.instance(ProxyInterfaceImpl.class);
        String result=proxyInterface.proxyTest("chenbk");
        System.out.println(result);
    }

}
