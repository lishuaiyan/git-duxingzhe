package com.k.multithread.chapter07.quarter01;

import java.lang.reflect.Constructor;

public class DiningPhilosopherProplem {
    public static void main(String[] args) throws Exception {
        int numOfPhilosophers;
        numOfPhilosophers = args.length > 0 ? Integer.valueOf(args[0]) : 2;
        //创建筷子
        Chopstick[] chopsticks = new Chopstick[numOfPhilosophers];
        for (int i = 0; i < numOfPhilosophers; i++) {
            chopsticks[i] = new Chopstick(i);
        }
        String philosopherImplClassName = System.getProperty("x.philo.impl");
        if (null == philosopherImplClassName) {
            philosopherImplClassName = "DeadlockingPhilosopher";
        }

        for (int i = 0; i < numOfPhilosophers; i++) {
            //创建哲学家
            createPhilosopher(philosopherImplClassName, i, chopsticks);
        }

    }

    private static void createPhilosopher(String philosopherImplClassName, int id, Chopstick[] chopsticks) throws Exception {
        int numOfphilosophers = chopsticks.length;
        @SuppressWarnings("unchecked")
        Class<Philosopher> philosopherClass = (Class<Philosopher>) Class.forName(DiningPhilosopherProplem.class.getPackage().getName()
                + "." + philosopherImplClassName);
        Constructor<Philosopher> constructor = philosopherClass.getConstructor(int.class, Chopstick.class, Chopstick.class);
        Philosopher philosopher = constructor.newInstance(id, chopsticks[id], chopsticks[(id + 1) % numOfphilosophers]);
        philosopher.start();

    }
}
