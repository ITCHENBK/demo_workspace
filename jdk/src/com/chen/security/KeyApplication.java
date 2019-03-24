package com.chen.security;

import sun.security.provider.DSAPrivateKey;
import sun.security.provider.DSAPublicKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class KeyApplication {

    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("dsa");
        SecureRandom random= SecureRandom.getInstance("SHA1PRNG","SUN");
        random.setSeed(new byte[]{1,2,3,4});
        /**
         * jdk8: key必须在[512,1024]之间,并且是64的倍数。有的JDK版本要求是8的倍数，这要根据实际情况和需求设定
         */
        keyPairGenerator.initialize(576, random);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        DSAPublicKey publicKey=(DSAPublicKey)keyPair.getPublic();
        DSAPrivateKey privateKey=(DSAPrivateKey)keyPair.getPrivate();
        System.out.println(publicKey.getFormat());
        System.out.println(privateKey.getFormat());
        System.out.println(publicKey);
        System.out.println(privateKey);
    }

}
