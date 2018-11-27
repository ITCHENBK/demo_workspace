package com.chenbk.utils.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by chenbk on 2018/1/7.
 */
public class IdUtil {

    public static String createId(Date date) {
        return (new SimpleDateFormat("yyyyMMdddhhmmss")).format(date) + String.format("%04d", new Random().nextInt(9999));
    }

    public static String createId() {
        return createId(new Date());
    }


    public static String createUUID(){
        return UUID.randomUUID().toString();
    }


    /**
     * 内容长度 4个字节
     *
     * @param num
     * @return
     */
    public byte[] contentLength(int num) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (num & 0xff);// 最低位
        targets[2] = (byte) ((num >> 8) & 0xff);// 次低位
        targets[1] = (byte) ((num >> 16) & 0xff);// 次高位
        targets[0] = (byte) (num >>> 24);// 最高位,无符号右移。
        return targets;
    }

}
