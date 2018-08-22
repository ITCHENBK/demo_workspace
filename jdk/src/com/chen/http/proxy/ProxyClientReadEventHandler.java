package com.chen.http.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Kang on 2018/3/7.
 */
public class ProxyClientReadEventHandler extends AbstractReadEventHandler {

    private static Logger logger= LoggerFactory.getLogger(ProxyClientReadEventHandler.class);

    @Override
    public void handle(byte[] buffer, Context context) {
        logger.info("client read");
        SocketChannel socketChannel=context.getSocketChannel();
        SocketChannel clientSocketChannel=context.getClientSocketChannel();
        try {
            socketChannel.write(ByteBuffer.wrap(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socketChannel!=null)
                    socketChannel.close();
                if(clientSocketChannel!=null)
                    clientSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
