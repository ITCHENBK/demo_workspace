package com.chen.reflect;

/**
 * Created by Kang on 2017/9/19.
 */
public class Object2 extends Object1<String> implements Interface1{

    public static String object2Name="Object2";

    public static String getObject2Name(){
        return object2Name;
    }

    private String name2;

    private int id;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


}
