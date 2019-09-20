package com.k.multithread.chapter05;

import com.k.multithread.util.Debug;
import com.k.multithread.util.Tools;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用条件变量实现等待超时控制
 */
public class TimeoutWaitWithCondition {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condtition = lock.newCondition();
    private static boolean ready = false;
    protected static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    lock.lock();
                    try {
                        ready = random.nextInt(100) < 5 ? true : false;
                        if (ready) {
                            condtition.signal();
                        }
                    } finally {
                        lock.unlock();
                    }
                    //使当前线程暂停一段时间（随机）
                    Tools.randomPause(500);
                }// for循环结束
            }
        };
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }
    public static void waiter(final long timeOut) throws InterruptedException {
        if (timeOut < 0) {
            throw new IllegalArgumentException();
        }
        //计算等待的最后期限
        final Date deadline = new Date(System.currentTimeMillis() + timeOut);
        //是否继续等待
        boolean continueToWait = true;
        lock.lock();
        try {
            while (!ready) {
                Debug.info("still not ready, continue to wait:%s", continueToWait);
                //等待未超时，继续等待
                if (!continueToWait) {
                    //等待超时退出
                    Debug.error("Wait timed out , unable to execution target action!");
                    return;
                }
                continueToWait = condtition.awaitUntil(deadline);
            }//while 循环结束
            //执行目标动作
            guarededAction();

        } finally {
            lock.unlock();
        }
    }
    private static void guarededAction() {
        Debug.info("Take some action.");
        //...
    }
}
