package com.chenbk.utils.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Kang on 2018/8/12.
 */
public class ProxyGeneratorUtil {


    public static void writeJdkProxyClassFile(String path,Class ... clazzs)throws IOException{
        byte[] classFile=ProxyGenerator.generateProxyClass("ProxyTest",clazzs);
        Files.write(Paths.get(path),classFile);

    }


    public static void writeCglibProxyClassFile(String path,Class  clazz){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
        CglibProxyInstance.instance(clazz);
    }


    @Test
    public  void createJdkProxyClass(){
        try {
            ProxyGeneratorUtil.writeJdkProxyClassFile("D:\\test\\ProxyTest.class",ProxyInterface.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void createCglibProxyClass(){
        ProxyGeneratorUtil.writeCglibProxyClassFile("D:\\test\\ProxyTest1",ProxyInterfaceImpl.class);
    }
}
