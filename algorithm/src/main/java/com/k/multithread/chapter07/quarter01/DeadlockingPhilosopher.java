package com.k.multithread.chapter07.quarter01;

import com.k.multithread.util.Debug;

/**
 * 能导致死锁的哲学家模型
 */
public class DeadlockingPhilosopher extends AbstractPhilosopher {
    public DeadlockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized(left) {
            Debug.info("%s is pincking up %s on his left... %n", this, left);
            left.pickUp();//拿起左手边的筷子
            synchronized (right) {
                Debug.info("%s is picking up %s on his right...%n", this, right);
                right.pickUp();//拿起右手边的筷子
                doEat(); //同时拿起两根筷子的时候才能吃饭
                right.putDown();
            }
            left.putDown();
        }
    }
}
