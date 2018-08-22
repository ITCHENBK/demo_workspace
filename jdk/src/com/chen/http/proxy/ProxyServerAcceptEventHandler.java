package com.chen.http.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.UUID;

/**
 * Created by Kang on 2018/3/7.
 */
public class ProxyServerAcceptEventHandler implements NioEventHandler {

    private static Logger logger = LoggerFactory.getLogger(ProxyServerAcceptEventHandler.class);

    private NioEventHandler nioEventHandler;

    public ProxyServerAcceptEventHandler(NioEventHandler nioEventHandler) {
        this.nioEventHandler = nioEventHandler;
    }

    @Override
    public void handle(SelectionKey key, Context context) {
        if (context == null)
            context = new Context();
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        context.setId(UUID.randomUUID().toString());
        context.setServerSocketChannel(ssChannel);
        logger.info("id :" + context.getId() + "    accept start");
        try {
            SocketChannel sc = ssChannel.accept();
            sc.configureBlocking(false);
            ChannelBinder binder = new ChannelBinder();
            context.setSocketChannel(sc);
            binder.setContext(context);
            NioEventHandlers nioEventHandlers = new NioEventHandlers();
            nioEventHandlers.setReadtHandler(nioEventHandler);
            binder.setHandlers(nioEventHandlers);
            SelectorManager.registerReadEvent(sc, binder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NioEventHandler getNioEventHandler() {
        return nioEventHandler;
    }


}
