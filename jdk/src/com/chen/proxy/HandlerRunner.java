package com.chen.proxy;

import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * Created by Kang on 2018/3/7.
 */
public class HandlerRunner implements Runnable {
    private SocketChannel channel;

    private List<Handler> handlers;

    public HandlerRunner(SocketChannel channel, List<Handler> handlers) {
        this.channel = channel;
        this.handlers = handlers;
    }

    @Override
    public void run() {

    }
}
