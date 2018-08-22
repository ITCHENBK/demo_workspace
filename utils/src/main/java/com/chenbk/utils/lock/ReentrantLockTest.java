package com.chenbk.utils.lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Kang on 2018/1/19.
 */
public class ReentrantLockTest {

    public static void main(String... args) {
        ReentrantLock lock = new ReentrantLock();
        Thread current = Thread.currentThread();
        Thread thread = new Thread() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println("lock2");
            }
        };
        thread.start();
        try {
            Thread.sleep(8000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lock1");
        thread.interrupt();


    }


}
