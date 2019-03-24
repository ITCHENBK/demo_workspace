package com.chen;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kang on 2017/8/7.
 */
public class HelloWorld {

    private static final Logger logger= LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args){
        logger.info("开始");
        System.out.println(Integer.SIZE);
        retry:
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.println("i="+i+",j="+j);
                if(j==5){
                    break retry;
                }
            }
        }

    }

}
