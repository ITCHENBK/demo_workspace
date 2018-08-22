package com.chen.reflect;

/**
 * Created by Kang on 2018/8/22.
 */
public class Person {


    public Person(){}

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private Person(String name){this.name=name;}

    public static Long count=0L;

    private String name;

    public String id;

    public static Long getCount() {
        return count;
    }

    public static void setCount(Long count) {
        Person.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
