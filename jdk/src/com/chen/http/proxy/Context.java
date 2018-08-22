package com.chen.http.proxy;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Kang on 2018/3/5.
 */
public class Context {

    private String id;

    private byte[] requestBuffer;

    private byte[] responseBuffer;

    private ServerSocketChannel serverSocketChannel;

    private SocketChannel socketChannel;

    private SocketChannel clientSocketChannel;

    public byte[] getRequestBuffer() {
        return requestBuffer;
    }

    public void setRequestBuffer(byte[] requestBuffer) {
        this.requestBuffer = requestBuffer;
    }

    public byte[] getResponseBuffer() {
        return responseBuffer;
    }

    public void setResponseBuffer(byte[] responseBuffer) {
        this.responseBuffer = responseBuffer;
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public SocketChannel getClientSocketChannel() {
        return clientSocketChannel;
    }

    public void setClientSocketChannel(SocketChannel clientSocketChannel) {
        this.clientSocketChannel = clientSocketChannel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


