package com.k.multithread.chapter05;

import com.k.multithread.util.Debug;
import com.k.multithread.util.Tools;

import java.util.concurrent.CountDownLatch;

/**
 * 一个线程多次执行CountDownLatch.countDown()示例
 */
public class CountDownLatchExample {
    private static final CountDownLatch latch = new CountDownLatch(9);
    private static int data;
    public static void main(String[] args) throws InterruptedException {
        Thread workThread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    data = i;
                    latch.countDown();
                    //使当前线程暂停一段时间
                    Tools.randomPause(1000);
                }
            }
        };
        workThread.start();
        latch.await();
        Debug.info("It's done. data=%d", data);
    }
}
