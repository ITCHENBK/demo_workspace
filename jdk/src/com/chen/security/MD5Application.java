package com.chen.security;


import java.security.MessageDigest;

public class MD5Application {

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }

        }
        return hs.toUpperCase();
    }


    public static void main(String[] args) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        System.out.println(byte2hex(messageDigest.digest("hello world".getBytes())));
    }


}
