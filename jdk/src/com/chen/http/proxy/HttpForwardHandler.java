package com.chen.http.proxy;

/**
 * Created by Kang on 2018/3/6.
 */
public class HttpForwardHandler implements Handler {
    @Override
    public boolean isAccess(byte[] request, Context context) {
        return true;
    }

    @Override
    public byte[] handler(byte[] request, Context context) {
//        SocketChannel channel=SocketChannel.open();
//        channel.configureBlocking(false);
//        channel.connect(new InetSocketAddress("10.124.142.87",80));
        return new byte[0];
    }


}
