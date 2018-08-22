package com.chen.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Kang on 2018/8/22.
 */
public class Application {


    private Class clazz1;

    @org.junit.Test
    public void testClass() throws Exception{
        Class clazz1=Person.class;
        System.out.println(clazz1);
        Person person=new Person();
        Class clazz2=person.getClass();
        System.out.println(clazz2);
        Class clazz3=Class.forName("com.chen.reflect.Person");
        System.out.println(clazz3);

        Field[] fields=clazz1.getFields();
        for(Field field:fields){
            System.out.println(field);
        }
        fields=clazz1.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field);
        }
        System.out.println("======================Student================================");
        Class studentClazz=Student.class;
        fields=studentClazz.getFields();
        for(Field field:fields){
            System.out.println(field);
        }
        fields=studentClazz.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field);
        }
        System.out.println("======================================================");
        System.out.println(clazz1.getField("id"));
        System.out.println(clazz1.getDeclaredField("name"));


        System.out.println("=====================Method==============================");
        Method[] methods = clazz1.getMethods();
        for(Method method:methods){
            System.out.println(method);
        }
        System.out.println("=====================Declare Method==============================");
        methods = clazz1.getDeclaredMethods();
        for(Method method:methods){
            System.out.println(method);
        }
        System.out.println("=======================================================");
        System.out.println(clazz1.getMethod("setId",String.class));
        System.out.println(clazz1.getDeclaredMethod("setId",String.class));

        System.out.println("=====================Constructor==============================");
        Constructor[] constructors=clazz1.getConstructors();
        for(Constructor constructor:constructors){
            System.out.println(constructor);
        }
        System.out.println("=====================Declare Constructor==============================");
        constructors=clazz1.getDeclaredConstructors();
        for(Constructor constructor:constructors){
            System.out.println(constructor);
        }
        System.out.println("==============================================================");
        System.out.println(clazz1.getConstructor(String.class,String.class));
        System.out.println(clazz1.getDeclaredConstructor(String.class,String.class));

        System.out.println("=======================Annotation==============================");
        //clazz1.getAnnotations()
        //clazz1.getAnnotation();
        //clazz1.getAnnotatedInterfaces()
        clazz1.getSuperclass();
        clazz1.getGenericSuperclass();
        clazz1.getInterfaces();
        clazz1.getGenericInterfaces();

        Field field=clazz1.getDeclaredField("id");
        Key key=field.getAnnotation(Key.class);
        System.out.println(key);
        Method method=clazz1.getMethod("setId",String.class);
        Constructor constructor=clazz1.getConstructor();
    }
}
