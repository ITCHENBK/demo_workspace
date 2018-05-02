package com.chen.test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Kang on 2017/8/8.
 */
public class FutureDemo {
    public static void main(String ... args) throws Exception{
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 100;
            }
        };
        FutureTask<Integer> futureTask=new FutureTask<Integer>(callable);
        Thread thread=new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });

    }
}
