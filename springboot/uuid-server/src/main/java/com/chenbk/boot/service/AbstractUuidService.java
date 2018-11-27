package com.chenbk.boot.service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Kang on 2018/7/5.
 */
public abstract class AbstractUuidService implements UuidService {

    private static final long MAX_TIMESTAMP = 0B11111111111111111111111111111111111111111L;

    private static final int MAX_TIMESTAMP_OFFSET = 22;

    private static final long MAX_DATA_CENTER_ID = 0B1111L;

    private static final int MAX_DATA_CENTER_ID_OFFSET = 18;

    private static final long MAX_WORKER_ID = 0B111111L;

    private static final int MAX_WORKER_ID_OFFSET = 12;

    private static final long MAX_SEQUENCE_ID = 0B111111111111L;

    private static final int MAX_SEQUENCE_ID_OFFSET = 0;


    private static AtomicLong TIMESTAMP = new AtomicLong(0);

    private static AtomicLong SEQUENCE_ID=new AtomicLong(0);

    @Override
    public long genUUID() {
        return genUUID(getTimestamp(),getDataCenterId(),getWorkerId(),getSequenceId());
    }

    public long getTimestamp(){
        return TIMESTAMP.updateAndGet(x->{
            long nextTimestamp=System.currentTimeMillis();
            if(nextTimestamp<x){
                throw new RuntimeException();
            }
            return nextTimestamp;
        });
    }

    public abstract long getDataCenterId();

    public abstract long getWorkerId();

    public long getSequenceId(){
        return SEQUENCE_ID.getAndUpdate(x->x+1>MAX_SEQUENCE_ID?0:x+1);
    }

    private long genUUID(long timestamp, long datacenterId, long workerId, long sequenceId) {
        long uuid = 0L;
        if (timestamp > MAX_TIMESTAMP) {
            throw new IllegalArgumentException();
        }
        if (datacenterId > MAX_DATA_CENTER_ID) {
            throw new IllegalArgumentException();
        }
        if (workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException();
        }
        if (sequenceId > MAX_SEQUENCE_ID) {
            throw new IllegalArgumentException();
        }
        uuid |= (timestamp << MAX_TIMESTAMP_OFFSET);
        uuid |= (datacenterId << MAX_DATA_CENTER_ID_OFFSET);
        uuid |= (workerId << MAX_WORKER_ID_OFFSET);
        uuid |= (sequenceId << MAX_SEQUENCE_ID_OFFSET);
        return uuid;
    }

}
