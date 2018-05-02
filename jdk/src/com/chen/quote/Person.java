package com.chen.quote;

/**
 * Created by Kang on 2017/8/7.
 */
public class Person {

    private String  name;

    public Person(){}

    public Person(String name){this.name=name;}

    public static void staticMethod(Person person1,Person person2){
        System.out.println("staticMethod  person1: "+person1.toString());
        System.out.println("staticMethod  person2: "+person2.toString());
    }
    public void instanceMethod(Person person1){
        System.out.println("instanceMethod  this: "+this.toString());
        System.out.println("instanceMethod  person1: "+person1.toString());
    }

    public String toString(){ return name;}
}
