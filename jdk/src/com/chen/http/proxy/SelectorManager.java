package com.chen.http.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kang on 2018/3/6.
 */
public class SelectorManager {

    private static Logger logger= LoggerFactory.getLogger(SelectorManager.class);


    private static Selector selector;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static volatile boolean isRun = false;


    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void init() {

    }

    public static void registerReadEvent(SelectableChannel channel, ChannelBinder binder) throws IOException {
        channel.register(selector, SelectionKey.OP_READ, binder);
    }

    public static void registerAcceptEvent(SelectableChannel channel, ChannelBinder binder) throws IOException {
        channel.register(selector, SelectionKey.OP_ACCEPT, binder);
    }

    public static void registerConnectAndReadEvent(SelectableChannel channel, ChannelBinder binder) throws IOException {
        channel.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_READ, binder);
    }




    public static synchronized void runOnce() {
        if (!isRun)
            run();
        isRun = true;
    }

    private static void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            int n = 0;
            try {
                n = selector.selectNow();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (n == 0) {
                continue; // nothing to do
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                logger.info(selectionKey.channel()+"  ####  "+selectionKey.interestOps());
                Object o = selectionKey.attachment();

                if (o instanceof ChannelBinder) {
                    ChannelBinder binder=(ChannelBinder) o;
                    Context context = binder.getContext();
                    NioEventHandlers handlers=binder.getHandlers();
                    NioEventHandler eventHandler=null;
                    boolean async=false;
                    int operation=0;
                    if(selectionKey.isAcceptable()){
                        eventHandler=handlers.getAcceptHandler();
                        async=handlers.isAcceptAsync();
                        operation=SelectionKey.OP_ACCEPT;
                    }else if(selectionKey.isReadable()){
                        eventHandler=handlers.getReadtHandler();
                        async=handlers.isReadAsync();
                        operation=SelectionKey.OP_READ;
                    }else if(selectionKey.isConnectable()){
                        eventHandler=handlers.getConnectHandler();
                        async=handlers.isConnectAsync();
                        operation=SelectionKey.OP_CONNECT;
                    }
                    if(eventHandler==null){
                        iterator.remove();
                        continue;
                    }

                    if (async) {
                        final int op=operation;
                        final NioEventHandler h=eventHandler;
                        selectionKey.interestOps(selectionKey.interestOps() & (~op));
                        threadPool.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    h.handle(selectionKey, context);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (selectionKey.isValid()) {
                                    selectionKey.interestOps(selectionKey.interestOps() | op);
                                }
                            }
                        });
                    } else {
                        eventHandler.handle(selectionKey,context);
                    }
                }
                iterator.remove();
            }
        }
    }

}
