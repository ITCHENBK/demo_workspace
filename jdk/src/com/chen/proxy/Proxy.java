package com.chen.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenbk on 2018/3/2.
 */
public class Proxy {

    private static Logger logger= LoggerFactory.getLogger(Proxy.class);

    private static final int PORT = 8080;

    private static ConcurrentMap<String,Object> handlerMap=new ConcurrentHashMap<>();


    private static void init(){

    }

    public static void go(int port,List<Handler> handlers){
        Selector selector = null;
        try {
            ServerSocketChannel ssChannel = ServerSocketChannel.open();

            ssChannel.configureBlocking(false);

            ssChannel.bind(new InetSocketAddress(port));

            ProxyReadEventHandler proxyReadEventHandler=new ProxyReadEventHandler(handlers);

            ProxyForwardReadEventHandler proxyForwardReadEventHandler=new ProxyForwardReadEventHandler();
            ProxyServerAcceptEventHandler proxyServerAcceptEventHandler=new ProxyServerAcceptEventHandler(proxyReadEventHandler);


            NioEventHandlers nioEventHandlers=new NioEventHandlers();
            nioEventHandlers.setAcceptHandler(proxyServerAcceptEventHandler);
            nioEventHandlers.setAcceptAsync(true);

            ChannelBinder channelBinder=new ChannelBinder();
            channelBinder.setHandlers(nioEventHandlers);

            SelectorManager.registerAcceptEvent(ssChannel,channelBinder);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        InetAddress ip=null;
        try {
            ip=InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        logger.info("HOST:"+ ip+" PORT:"+port+" start");

        SelectorManager.runOnce();

        logger.info("HOST:"+ ip+" PORT:"+port+" stop");

    }

    public static void main(String... args) {

        Proxy.go(PORT, Collections.singletonList(new Handler() {
            @Override
            public boolean isAccess(byte[] request, Context context) {
                return true;
            }

            @Override
            public byte[] handler(byte[] request, Context context) {
                logger.info("id :"+context.getId()+"     ###"+new String(request));
                return request;
            }
        }));


    }

}
