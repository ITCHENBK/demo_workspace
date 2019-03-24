package com.chen.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureApplication {
    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyPairGen=KeyPairGenerator.getInstance("dsa");
        keyPairGen.initialize(1024);
        KeyPair keyPair= keyPairGen.generateKeyPair();
        PublicKey puk=keyPair.getPublic();
        PrivateKey pik=keyPair.getPrivate();

        String data="Hello, Java.";
        Signature signature= Signature.getInstance("SHA1withDSA");

        // private key sign
        signature.initSign(pik);
        signature.update(data.getBytes());
        byte[] signinfo=signature.sign();

        // public key resolve sign
        signature.initVerify(puk);
        boolean ok=signature.verify(signinfo);
        System.out.println(ok);

        signature.update(data.getBytes());
        ok=signature.verify(signinfo);
        System.out.println(ok);
    }
}
