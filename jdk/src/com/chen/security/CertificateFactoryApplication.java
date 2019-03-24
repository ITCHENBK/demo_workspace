package com.chen.security;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

public class CertificateFactoryApplication {
    public static void main(String ... args)throws Exception{
        InputStream fis = CertificateFactoryApplication.class.getClassLoader().getResourceAsStream("ca.crt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        System.out.println(fis.available());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        while (bis.available() > 0) {
            Certificate cert = cf.generateCertificate(bis);
            System.out.println(cert.toString());
        }

//        X509Certificate c = null;
//        c = (X509Certificate) cf.generateCertificate(bis);
//        Principal p = c.getIssuerDN();
//        PublicKey pk = getPublicKey(p);
//        c.verify(pk);
//        InputStream crlFile = lookupCRLFile(p);
//        cf = CertificateFactory.getInstance("X509CRL");
//        X509CRL crl = (X509CRL) cf.generateCRL(crlFile);

    }

}
