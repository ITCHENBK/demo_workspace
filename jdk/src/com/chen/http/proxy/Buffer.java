package com.chen.http.proxy;

import java.io.UnsupportedEncodingException;

/**
 * Created by Kang on 2018/3/5.
 */
public class Buffer {


    private byte[] buffer;

    public Buffer(byte[] buffer) {
        this.buffer = buffer;
    }


    public byte[] getBytes(){
        return buffer;
    }

    public String getString(){
        try {
            return new String(buffer,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
