package com.chenbk.rxjava;


import io.reactivex.Observable;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.internal.schedulers.NewThreadWorker;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Kang on 2018/7/5.
 */
public class Demo1 {

    @Test
    public void test1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("123");
                e.onNext("321");
                e.onComplete();
                e.onNext("6666666");
            }
        }).subscribe(new Observer<String>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                this.disposable = d;
                System.out.println("onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                System.out.println("onNext: " + value);
                if ("123".equals(value)) {
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete: " + disposable.isDisposed());
            }
        });
    }


    @Test
    public void test2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("123");
                e.onNext("321");
                e.onNext("456");
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s) + 10000;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("onNext: " + integer);
            }
        });
    }

    @Test
    public void test3() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Test
    public void test4() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println(Thread.currentThread().getName() + "  "+ "create");
                e.onNext(1);
            }
        }).subscribeOn(new Scheduler() {
            @Override
            public Worker createWorker() {
                return new NewThreadWorker(new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t =new Thread(r, "subscribeOn");
                        if (t.isDaemon())
                            t.setDaemon(false);
                        if (t.getPriority() != Thread.NORM_PRIORITY)
                            t.setPriority(Thread.NORM_PRIORITY);
                        return t;
                    }
                });
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(Thread.currentThread().getName() + "  "+ "doOnNext");
            }
        }).observeOn(new Scheduler() {
            @Override
            public Worker createWorker() {
                return new NewThreadWorker(new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t =new Thread(r, "observeOn");
                        if (t.isDaemon())
                            t.setDaemon(false);
                        if (t.getPriority() != Thread.NORM_PRIORITY)
                            t.setPriority(Thread.NORM_PRIORITY);
                        return t;
                    }
                });
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(Thread.currentThread().getName() + "  "+ "subscribe");
            }
        });


    }

}
