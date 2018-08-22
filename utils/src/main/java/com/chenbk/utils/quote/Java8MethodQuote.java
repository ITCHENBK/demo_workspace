package com.chenbk.utils.quote;

/**
 * Created by Kang on 2017/8/7.
 */
public class Java8MethodQuote {
    public static void main(String [] args){
        //构造器引用
        System.out.println("构造器引用");
        Construct<Person> construct=Person::new;
        Person p=construct.load();
        //静态方法引用
        System.out.println("静态方法引用");
        Intf2<Person> intf2=Person::staticMethod;
        Person person1=new Person("Person1");
        Person person2=new Person("Person2");
        intf2.intf(person1,person2);
        //特定类的任意对象的方法引用
        System.out.println("特定类的任意对象的方法引用");
        intf2=Person::instanceMethod;
        intf2.intf(person1,person2);
        //特定对象的方法引用
        System.out.println("特定对象的方法引用");
        Person person=new Person("Person");
        Intf1<Person> intf1=person::instanceMethod;
        intf1.intf(person1);

        Intf3 intf3=User::instanceMethod;

    }
}
