package com.chenbk.boot.config;

public class DataSourceHolder {

    private static final ThreadLocal<String> threadLocal=new ThreadLocal<String>();


    public static void  setDatasource(String key){
        threadLocal.set(key);
    }

    public static String getDatasource(){
        return threadLocal.get();
    }

    public static void clearDatasource(){
        threadLocal.remove();
    }
}