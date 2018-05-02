package com.chen.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by Kang on 2018/3/7.
 */
public class ProxyClientConnectEventHandler implements NioEventHandler {

    private static Logger logger= LoggerFactory.getLogger(ProxyClientConnectEventHandler.class);

    @Override
    public void handle(SelectionKey key, Context context) {
        logger.info("client connect");
        try {
            SocketChannel socketChannel=(SocketChannel) key.channel();
            socketChannel.write(ByteBuffer.wrap(context.getRequestBuffer()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
