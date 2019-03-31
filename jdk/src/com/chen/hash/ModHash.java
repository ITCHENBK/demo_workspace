package com.chen.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModHash {

    public static int COUNT=1000000;

    public static int NODE1=4;

    public static int NODE2=3;

    // 4  3  0.750005
    // 4  5  0.800104
    // 4  6  0.666667
    // 4  7  0.857145
    // 4  8  0.49999

    public static void main(String ... args)throws Exception{
        Map<Integer,List<String>> map=new HashMap<>();

        for(int i=0;i<COUNT;i++){
            String s=i+"";
            int mod=(s.hashCode())%NODE1;
            if(map.containsKey(mod)){
                map.get(mod).add(s);
            }else{
                List<String> l=new ArrayList<>();
                l.add(s);
                map.put(mod,l);
            }
        }

        int i=0;
        for(Map.Entry<Integer,List<String>> entry:map.entrySet()){
           Integer key = entry.getKey();
           for(String s:entry.getValue()){
               if((s.hashCode()%NODE2)!= key){
                   i++;
               }
           }
        }
        System.out.println(i/(COUNT+0.0));
    }

}
