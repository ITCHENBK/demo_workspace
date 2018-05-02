package com.chen.quote;

/**
 * Created by Kang on 2017/8/4.
 */
@FunctionalInterface
public interface Construct<T> {
    T load();
}
