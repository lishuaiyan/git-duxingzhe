package com.k.multithread.chapter05.quarter05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 通用任务执行器
 */
public class TaskRunner {
    protected final BlockingQueue<Runnable> channel;
    protected volatile Thread workerThread;
    public TaskRunner(BlockingQueue<Runnable> channel) {
        this.channel = channel;
        this.workerThread = new WorkerThread();

    }
    public TaskRunner() {
        this(new LinkedBlockingDeque<Runnable>());
    }
    public void init() {
        final Thread t = workerThread;
        if (null != t) {
            t.start();
        }
    }
    public void submit(Runnable task) throws InterruptedException {
        channel.put(task);
    }
    class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task = null;
            try {
                //注意：下面这种代码写法实际上可能导致工作者线程永远无法终止
                //在5.6中我们将会解决这个问题
                for (;;) {
                    task = channel.take();
                    try {
                        task.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }//for循环结束
            } catch (InterruptedException e) {
                //什么也不做
            }
        }//run方法结束
    }//WorkerThread结束
}
