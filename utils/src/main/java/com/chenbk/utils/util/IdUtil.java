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
}
