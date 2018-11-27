package com.chenbk.utils.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Objects;

/**
 * Created by Kang on 2018/8/19.
 */
public class ReflectionUtils {


    /**
     * 获取字段
     * @param clazz
     * @param name
     * @return
     */
    public static Field findField(Class<?> clazz, String name) {
        Objects.requireNonNull(clazz, "Class must not be null");
        Objects.requireNonNull(name, "Method name must not be null");
        Class<?> searchClass = clazz;
        while (searchClass != null) {
            try {
                Field field = searchClass.getDeclaredField(name);
                return field;
            } catch (NoSuchFieldException e) {

            }
            searchClass = searchClass.getSuperclass();
        }
        return null;
    }

    /**
     * 给字段设置值
     * @param field
     * @param target
     * @param value
     */
    public static void setField(Field field, Object target, Object value) {
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not access field: " + e.getMessage());
        }
    }

    /**
     * 给字段设置值
     * @param name
     * @param target
     * @param value
     */
    public static void setField(String name, Object target, Object value) {
        try {
            Field field=findField(target.getClass(),name);
            makeAccessible(field);
            field.set(target, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not access field: " + e.getMessage());
        }
    }

    /**
     * 获取字段的值
     * @param field
     * @param target
     * @return
     */
    public static Object getField(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not access field: " + e.getMessage());
        }

    }

    /**
     * 获取字段的值
     * @param name
     * @param target
     * @return
     */
    public static Object getField(String name, Object target) {
        try {
            Field field=findField(target.getClass(),name);
            makeAccessible(field);
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not access field: " + e.getMessage());
        }

    }

    /**
     * 获取方法
     * @param clazz
     * @param name
     * @param paramTypes
     * @return
     */
    public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        Objects.requireNonNull(clazz, "Class must not be null");
        Objects.requireNonNull(name, "Method name must not be null");
        Class<?> searchClass = clazz;
        while (searchClass != null) {
            try {
                Method method = searchClass.getDeclaredMethod(name, paramTypes);
                return method;
            } catch (NoSuchMethodException e) {

            }
            searchClass = searchClass.getSuperclass();
        }
        return null;
    }

    /**
     * 方法调用
     * @param method
     * @param target
     * @param args
     * @return
     */
    public static Object invokeMethod(Method method,Object target,Object ... args){
        try {
           return  method.invoke(target,args);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not access method: " + e.getMessage());
        } catch (InvocationTargetException e) {
            handleInvocationTargetException(e);
            /**
             * 这行代码永远不会执行
             */
            throw new RuntimeException();
        }
    }


    /**
     * 设置字段访问属性
     * @param field
     */
    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                || !Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }


    /**
     * 设置方法访问属性
     * @param method
     */
    public static void makeAccessible(Method method) {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())
                || !Modifier.isFinal(method.getModifiers())) && !method.isAccessible()) {
            method.setAccessible(true);
        }
    }

    /**
     * 设置构造器访问属性
     * @param ctor
     */
    public static void makeAccessible(Constructor<?> ctor){
        if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())
                || !Modifier.isFinal(ctor.getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }

    }


    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     * @param clazz
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz, int index){
        Type genType = clazz.getGenericSuperclass();

        if(!(genType instanceof ParameterizedType)){
            return Object.class;
        }

        Type [] params = ((ParameterizedType)genType).getActualTypeArguments();

        if(index >= params.length || index < 0){
            return Object.class;
        }

        if(!(params[index] instanceof Class)){
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     * @param <T>
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static<T> Class<T> getSuperGenericType(Class clazz){
        return getSuperClassGenricType(clazz, 0);
    }




    public static void handleInvocationTargetException(InvocationTargetException ex) {
        Throwable targeException=ex.getTargetException();
        if (targeException instanceof RuntimeException) {
            throw (RuntimeException)targeException;
        }
        if (targeException instanceof Error) {
            throw (Error) targeException;
        }
        throw new UndeclaredThrowableException(targeException);
    }

}
