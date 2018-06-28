package com.chen.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kang on 2018/5/2.
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String ... args){
        List<OOMObject> list=new ArrayList<>();
        while(true){
            list.add(new OOMObject());
        }
    }

}
