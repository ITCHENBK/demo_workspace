package com.chenbk.utils.quote;

/**
 * Created by Kang on 2017/8/14.
 */
public class User {
    private String name;

    public User(){}

    public User(String name){ this.name=name;}

    public void instanceMethod(Person person1){
        System.out.println("instanceMethod  this: "+this.toString());
        System.out.println("instanceMethod  person1: "+person1.toString());
    }
}
