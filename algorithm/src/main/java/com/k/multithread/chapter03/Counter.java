package com.k.multithread.chapter03;

/**
 * 基于volatile的简易读写锁
 */
public class Counter {
    private volatile long count;
    public long value() {
        return count;
    }
    public void increment() {
        synchronized (this) {
            count++;
        }
    }
}
