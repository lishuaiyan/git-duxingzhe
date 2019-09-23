package com.k.multithread.chapter05.quarter05;

import java.util.HashSet;
import java.util.Set;


public enum ThreadTerminationRegistry {
    INSTANCE;
    private final Set<Handler> handlers = new HashSet<>();
    public synchronized void register(Handler handler) {
        handlers.add(handler);
    }
    public void clearThreads() {
        //为保障线程安全，在遍历时将hadlers复制一份
        final Set<Handler> handlersSnapshot;
        synchronized (this) {
            handlersSnapshot = new HashSet<>(handlers);
        }
        for (Handler handler : handlersSnapshot) {
            try {
                handler.terminate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 线程终止处理器
     */
    public static interface Handler {
        void terminate();
    }
}
