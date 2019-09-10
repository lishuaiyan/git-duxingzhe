package com.k.multithread.chapter03;

import com.k.multithread.chapter02.CircularSeqGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过显式锁实现循环递增序列号生成器
 */
public class LockbasedCircularSeqGenerator implements CircularSeqGenerator {
    private short sequence = -1;
    private final Lock lock = new ReentrantLock();
    @Override
    public short nextSequence() {
        lock.lock();
        try {
            if (sequence >= 999) {
                sequence = 0;
            } else {
                sequence++;
            }
            return sequence;
        } finally {
            lock.unlock();
        }
    }
}
