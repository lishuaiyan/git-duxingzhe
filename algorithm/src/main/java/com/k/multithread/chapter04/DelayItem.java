package com.k.multithread.chapter04;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DelayItem {
    private long timeStamp;
    private AtomicInteger sampleCount = new AtomicInteger(0);
    private AtomicLong totalDelay = new AtomicLong(0);

    public DelayItem(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public AtomicInteger getSampleCount() {
        return sampleCount;
    }
    public void setSampleCount(AtomicInteger sampleCount) {
        this.sampleCount = sampleCount;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public AtomicLong getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDalay(AtomicLong totalDelay) {
        this.totalDelay = totalDelay;
    }
}

