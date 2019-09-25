package com.k.multithread.chapter06.quarter03;

import com.k.multithread.chapter03.Endpoint;
import com.k.multithread.chapter03.LoadBalancer;
import com.k.multithread.chapter03.Candidate;
import java.util.logging.Logger;

public class AbstractLoadBalancer implements LoadBalancer {
    private final static Logger LOGGER = Logger.getAnonymousLogger();
    //使用volatile变量替代锁（有条件替代）
    protected volatile Candidate candidate;
    //心跳线程
    private Thread heartbeatThread;
    @Override
    public void updateCandidate(final Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invalid candidate " + candidate);
        }
        //更新volatile变量candidate
        this.candidate = candidate;
    }


    @Override
    public Endpoint nextEndpoint() {
        return null;
    }
    //其他代码参见清单 3- 12
}
