package com.chen.http.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kang on 2018/3/7.
 */
public class ProxyReadEventHandler implements NioEventHandler {
    private static Logger logger= LoggerFactory.getLogger(ProxyReadEventHandler.class);


    private List<Handler> handlers;

    public ProxyReadEventHandler(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    @Override
    public void handle(SelectionKey key, Context context) {
        logger.info("id : "+context.getId()+"  read start");
        SocketChannel channel=(SocketChannel) key.channel();
        try {
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            List<byte[]> bytesList=new ArrayList<>();
            int count;
            while ((count = channel.read(byteBuffer)) > 0) {
                byteBuffer.flip(); // 将缓冲区置为可读
                byte[] bytes=new byte[byteBuffer.limit()];
                byteBuffer.get(bytes);
                bytesList.add(bytes);
                byteBuffer.clear(); // Empty buffer
            }
            int size=bytesList.stream().reduce(0,(x,y)->y.length,(x,y)->x+y);
            byte[] request=new byte[size];
            count=0;
            for(byte[] bs:bytesList){
                System.arraycopy(bs,0,request,count,bs.length);
                count+=bs.length;
                bs=null;
            }
            byte[] response=null;
            for(Handler handler:handlers){
                if(handler.isAccess(request,context))
                    request=handler.handler(request,context);
            }
            if (request!=null&&handlers!=null&&handlers.size()>0){
                channel.write(ByteBuffer.wrap(request));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(channel!=null){
                    channel.close();
                    context.setSocketChannel(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
