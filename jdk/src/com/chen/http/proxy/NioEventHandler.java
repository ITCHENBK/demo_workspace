package com.chen.http.proxy;

import java.nio.channels.SelectionKey;

/**
 * Created by Kang on 2018/3/6.
 */
public interface NioEventHandler {

    void handle(SelectionKey key,Context context);

}
