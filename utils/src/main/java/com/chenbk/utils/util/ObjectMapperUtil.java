package com.chenbk.utils.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by chenbk on 2018/1/6.
 * ObjectMapper实例化工具保证每个线程只有一个ObjectMapper被实例化
 */
public class ObjectMapperUtil {

    private static ThreadLocal<ObjectMapper> threadLocal = new ThreadLocal() {          //一个线程只有一个ObjectMapper实例化
        protected synchronized Object initialValue() {
                return new ObjectMapper();
        }
    };


    /**
     *
     * @return
     */
    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper=threadLocal.get();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }


    /**
     *
     * @param o
     * @return
     */
    public static String writeValueAsString(Object o){
        ObjectMapper objectMapper=threadLocal.get();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param s
     * @param clazz
     * @param <T>
     * @return
     */
    public  static <T> T readValue(String s,Class<T> clazz){
        ObjectMapper objectMapper=threadLocal.get();
        try {
            return objectMapper.readValue(s,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param content
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static  <T> T readValue(String content, TypeReference valueTypeRef){
        ObjectMapper objectMapper=threadLocal.get();
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param fromValue
     * @param toValueType
     * @param <T>
     * @return
     */
    public static <T>  T convertValue(Object fromValue, Class<T> toValueType){
        return  getObjectMapper().convertValue(fromValue,toValueType);
    }

    /**
     *
     * @param toValueType
     * @param fromValues
     * @param <T>
     * @return
     */
    public static <T>  T convertValue(Class<T> toValueType,Object ... fromValues ){
        Map sumMap=new LinkedHashMap();
        for(Object o:fromValues){
          Map map =  getObjectMapper().convertValue(o,Map.class);
          sumMap.putAll(map);
        }
        return  getObjectMapper().convertValue(sumMap,toValueType);
    }

}
