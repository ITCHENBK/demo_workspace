package com.chen.http.proxy;


/**
 * Created by Kang on 2018/3/5.
 */
public interface Handler {

    boolean isAccess(byte[] request, Context context);

    byte[] handler(byte[] request, Context context);

}
