package com.chen;




/**
 * Created by Kang on 2017/8/7.
 */
public class HelloWorld {

    public static void main(String[] args){

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
