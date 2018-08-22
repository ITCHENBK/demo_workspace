package com.chen.http.proxy;

/**
 * Created by Kang on 2018/3/13.
 */
public class NioEventHandlers {

    private NioEventHandler acceptHandler;

    private boolean acceptAsync=false;

    private NioEventHandler readtHandler;

    private boolean readAsync=false;

    private NioEventHandler connectHandler;

    private boolean connectAsync=false;

    public NioEventHandler getAcceptHandler() {
        return acceptHandler;
    }

    public void setAcceptHandler(NioEventHandler acceptHandler) {
        this.acceptHandler = acceptHandler;
    }

    public NioEventHandler getReadtHandler() {
        return readtHandler;
    }

    public void setReadtHandler(NioEventHandler readtHandler) {
        this.readtHandler = readtHandler;
    }

    public NioEventHandler getConnectHandler() {
        return connectHandler;
    }

    public void setConnectHandler(NioEventHandler connectHandler) {
        this.connectHandler = connectHandler;
    }

    public boolean isAcceptAsync() {
        return acceptAsync;
    }

    public void setAcceptAsync(boolean acceptAsync) {
        this.acceptAsync = acceptAsync;
    }

    public boolean isReadAsync() {
        return readAsync;
    }

    public void setReadAsync(boolean readAsync) {
        this.readAsync = readAsync;
    }

    public boolean isConnectAsync() {
        return connectAsync;
    }

    public void setConnectAsync(boolean connectAsync) {
        this.connectAsync = connectAsync;
    }
}
