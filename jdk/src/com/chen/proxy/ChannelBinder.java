package com.chen.proxy;

/**
 * Created by Kang on 2018/3/13.
 */
public class ChannelBinder {

    private Context context;

    private NioEventHandlers handlers;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NioEventHandlers getHandlers() {
        return handlers;
    }

    public void setHandlers(NioEventHandlers handlers) {
        this.handlers = handlers;
    }
}
