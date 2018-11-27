package com.chenbk.utils.proxy;

import org.junit.Test;

/**
 * Created by Kang on 2018/8/12.
 */
public class ProxyTest {


    public static void main(String ... args)throws Exception{
        ProxyInterface proxyInterface=new ProxyInterfaceImpl();

        proxyInterface= (ProxyInterface)JdkProxyInstance.instance(proxyInterface,ProxyInterface.class);

        String result= null;
        try {
            result = proxyInterface.proxyTest("chenbk");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }

    @Test
    public void jdkProxyTest()throws Exception{
        ProxyInterface proxyInterface=new ProxyInterfaceImpl();

        proxyInterface= (ProxyInterface)JdkProxyInstance.instance(proxyInterface,ProxyInterface.class);

        String result=proxyInterface.proxyTest("chenbk");

        System.out.println(result);

    }


    @Test
    public void cglibProxyTest()throws Exception{
        ProxyInterfaceImpl proxyInterface  =(ProxyInterfaceImpl)CglibProxyInstance.instance(ProxyInterfaceImpl.class);
        String result=proxyInterface.proxyTest("chenbk");
        System.out.println(result);
    }

}
