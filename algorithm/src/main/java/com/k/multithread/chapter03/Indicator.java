package com.k.multithread.chapter03;

import edu.princeton.cs.algs4.In;

import java.util.concurrent.atomic.AtomicLong;

public class Indicator {
    //保存当前类的唯一实例

        private static final Indicator INSTANCE = new Indicator();
    /**
     * 记录请求总数
     */
    private final AtomicLong requestCount = new AtomicLong(0);
    /**
     * 记录处理成功总数
     */
    private final AtomicLong successCount = new AtomicLong(0);
    /**
     * 记录处理失败总数
     */
    private final AtomicLong failureCount = new AtomicLong(0);
    private Indicator() {
        //什么也不做
    }
    //返回该类的唯一实例
    public static Indicator getInstance() {
        return INSTANCE;
    }
    public void newRequestReceived() {
        //使总请求数增加1.这里无需加锁
        requestCount.incrementAndGet();
    }
    public void newRequestSucessed() {
        //成功请求数增加1，这里无需加锁
        successCount.incrementAndGet();
    }
    public void newRequestFailed() {
        //使失败请求数增加1，这里无需加锁
        failureCount.incrementAndGet();
    }
    public long getRequestCount() {
        return requestCount.get();
    }
    public long getSuccessCount() {
        return successCount.get();
    }
    public long getFailureCount() {
        return failureCount.get();
    }
    public void reset() {
        requestCount.set(0);
        successCount.set(0);
        failureCount.set(0);
    }
    @Override
    public String toString() {
        return "Counter [requestCount=" + requestCount + ", successCount="
                + successCount + ", failureCount=" + failureCount + "]";
    }
}

