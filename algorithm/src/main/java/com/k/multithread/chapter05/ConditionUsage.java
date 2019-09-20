package com.k.multithread.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUsage {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public void aGuaredMethod() throws InterruptedException {
        lock.lock();
        try {
            while ("保护条件不成立" == null) {
                condition.await();
            }
            //执行目标动作
            doAction();
        } finally {
            lock.unlock();
        }
    }
    private void doAction() {
        //...
    }
    public void anNotificationMethod() throws InterruptedException {
        lock.lock();
        try {
            //更新共享变量
            changeState();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    private void changeState() {
        //...
    }
}
