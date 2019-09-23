package com.k.multithread.chapter05.quarter05;

import com.k.multithread.chapter04.AbstractLogReader;
import com.k.multithread.util.Debug;
import com.k.multithread.util.Tools;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadManagementContextListener implements ServletContextListerer {
    @Override
    public void contextDestroyed(ServletContextEvent ctxEvt) {
        //停止所有登记线程
        ThreadTerminationRegistry.INSTANCE.clearThreads();
    }
    @Override
    public void contextInitialized(ServletContextEvent ctxEvt) {
        //创建并启动一个数据库监控线程
        AbstractMonitorThread databaseMonitorThread;
        databaseMonitorThread = new AbstractMonitorThread (2000) {
            @Override
            protected void doMonitor() {
                Debug.info("Monitoring database...");
                //...
                //模拟实际消耗时间
                Tools.randomPause(1000);
            }
        };
        databaseMonitorThread.start();
    }
    /**
     * 抽象监控线程
     */
    static abstract class AbstractMonitorThread extends Thread {
        //监控周期
        private final long interval;
        //线程停止标记
        final AtomicBoolean terminationToken = new AtomicBoolean(false);
        public AbstractMonitorThread(long interval) {
            this.interval = interval;
            //设置守护线程
            setDaemon(true);
            ThreadTerminationRegistry.Handler handler;
            handler = new ThreadTerminationRegistry.Handler() {
                @Override
                public void terminate() {
                    terminationToken.set(true);
                    AbstractMonitorThread.this.interrupt();
                }
            };//语句 2
            ThreadTerminationRegistry.INSTANCE.register(handler);
        }
        @Override
        public void run() {
            try {
                while (!terminationToken.get()) {
                    doMonitor();
                    Thread.sleep(interval);
                }
            } catch (InterruptedException e) {
                //什么也不做
            }
            Debug.info("terminted:%s", Thread.currentThread());
        }
        //子类覆盖该方法来实现监控逻辑
        protected abstract void doMonitor();
    }
}
