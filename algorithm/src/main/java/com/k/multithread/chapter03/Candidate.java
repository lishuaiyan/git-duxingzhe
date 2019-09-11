package com.k.multithread.chapter03;

import java.util.Set;

public final class Candidate implements Iterable<Endpoint>{
    //下游部件节点列表
    private final Set<Endpoint> endpoints;
    //下游部件节点的权重
    public final int totalWeight;

    public Candidate(Set<Endpoint> endpoints) {
        int sum = 0;
        for (Endpoint endpoint)
    }
}
