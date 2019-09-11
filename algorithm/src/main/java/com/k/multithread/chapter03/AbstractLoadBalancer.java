package com.k.multithread.chapter03;

import sun.plugin2.main.server.HeartbeatThread;

import java.util.Random;
import java.util.logging.Logger;

public abstract class AbstractLoadBalancer implements LoadBalancer {
    private final static Logger LOGGER = Logger.getAnonymousLogger();
    //使用volatile变量替代锁（有条件替代）
    protected final Random random;
    protected Candidate candidate;

    //心跳线程
    private Thread heartbeatThread;
    public AbstractLoadBalancer(Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invaild candidate " + candidate);
        }
        this.candidate = candidate;
        random = new Random();
    }
    public synchronized void init() throws Exception {
        if (null == heartbeatThread) {
            heartbeatThread = new Thread(new HeartbeatTask(), "LB_Heartbeat");
            heartbeatThread.setDaemon(true);
            heartbeatThread.start();
        }
    }
    @Override
    public void updateCandidate(final Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invaild candidate " + candidate);
        }
        //更新volatile变量candidate
        this.candidate = candidate;
    }
    /**
     * 留给子类的抽象方法
     */
    @Override
    public abstract Endpoint nextEndpoint();
    protected void monitorEndpoints() {
        //读取volatile变量
        final Candidate currCandidate = candidate;
        boolean isTheEndpointOnline;
        //检测下游部件状态是否正常
        for (Endpoint endpoint : currCandidate) {
            isTheEndpointOnline = endpoint.isOnline();
            if (doDetect(endpoint) != isTheEndpointOnline) {
                endpoint.setOnline(!isTheEndpointOnline);
                //省略记录日志代码
            }
        }//for循环结束
    }
    //检测指定的节点是否在线
    private boolean doDetect(Endpoint endpoint) {
        //...
        return true;
    }
    private class HeartbeatTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    //检测节点列表中的所有节点是否在线
                    monitorEndpoints();
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                //什么也不做
            }
        }
    }//HeartbeatTask类结束
}
