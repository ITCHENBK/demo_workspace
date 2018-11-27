package com.chenbk.boot.service.impl;

import com.chenbk.boot.service.AbstractUuidService;
import org.springframework.stereotype.Service;

/**
 * Created by Kang on 2018/7/5.
 */
@Service
public class DefaultUuidServiceImpl extends AbstractUuidService {

    public long getDataCenterId(){
        return 1L;
    }

    public long getWorkerId(){
        return 1L;
    }

    public static void main(String ...args){
        String s=Long.toBinaryString(6420536494972473356L);
        System.out.println(s);
        System.out.println(s.length());
    }
}
