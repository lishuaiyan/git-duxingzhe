package com.k.multithread.chapter03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示线程转储显式锁信息的示例程序
 */
public class ExplicitLockInfo {
    private static final Lock lock = new ReentrantLock();
    private static int sharedData = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    try {
                        Thread.sleep(2200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sharedData = 1;
                } finally {
                    lock.unlock();
                }
            }
        });
        t.start();
        Thread.sleep(100);
        lock.lock();
        try {
            System.out.println("sharedData:" + sharedData);
        } finally {
            lock.unlock();
        }
    }
}
