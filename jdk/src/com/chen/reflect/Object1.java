package com.chen.reflect;

/**
 * Created by Kang on 2017/9/19.
 */
public class Object1<T> {
    public static String objectName="Object1";


    public static String getObjectName(){
        return objectName;
    }

    private T t;

    private String name;

    private int id;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
