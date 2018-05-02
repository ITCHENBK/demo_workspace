package com.chen.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kang on 2018/3/9.
 */
public class ExecutorServiceDemo {
    private static Logger logger= LoggerFactory.getLogger(ExecutorServiceDemo.class);

    private static ExecutorService threadPool= Executors.newFixedThreadPool(10);


    public static void main(String ... args){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName()+"  start");
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(Thread.currentThread().getName()+"  end");
            }
        };
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        while (true){

        }
    }
}
