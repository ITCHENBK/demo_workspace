package com.chen.http.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;


/**
 * Created by Kang on 2018/3/7.
 */
public class ProxyForwardReadEventHandler extends AbstractReadEventHandler {

    private static Logger logger= LoggerFactory.getLogger(ProxyReadEventHandler.class);

    private NioEventHandler connectEventHandler;

    private NioEventHandler readEvenetHandler;


    public ProxyForwardReadEventHandler() {
        connectEventHandler=new ProxyClientConnectEventHandler();
        readEvenetHandler=new ProxyClientReadEventHandler();
    }

    public NioEventHandler getConnectEventHandler() {
        return connectEventHandler;
    }

    public void setConnectEventHandler(NioEventHandler connectEventHandler) {
        this.connectEventHandler = connectEventHandler;
    }

    public NioEventHandler getReadEvenetHandler() {
        return readEvenetHandler;
    }

    public void setReadEvenetHandler(NioEventHandler readEvenetHandler) {
        this.readEvenetHandler = readEvenetHandler;
    }

    @Override
    public void handle(byte[] buffer, Context context) {
        logger.info("forward");
        try {
            SocketChannel channel=SocketChannel.open();
            channel.configureBlocking(false);

            Context c=new Context();
            NioEventHandlers handlers=new NioEventHandlers();
            handlers.setReadtHandler(readEvenetHandler);
            handlers.setConnectHandler(connectEventHandler);
            context.setRequestBuffer(buffer);
            context.setClientSocketChannel(channel);
            ChannelBinder binder=new ChannelBinder();
            binder.setContext(context);
            binder.setHandlers(handlers);
            SelectorManager.registerConnectAndReadEvent(channel,binder);
            channel.connect(new InetSocketAddress("127.0.0.1",8100));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
