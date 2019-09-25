package com.k.multithread.chapter06.quarter03;

import com.k.multithread.chapter03.Endpoint;
import com.k.multithread.util.ReadOnlyIterator;


import java.util.Iterator;
import java.util.Set;

public final class Candidate implements Iterable<Endpoint> {
    //下游部件节点列表
    private final Set<Endpoint> endpoints;
    //下游部件节点的总权重
    public final int totalWeight;
    public Candidate(Set<Endpoint> endpoints) {
        int sum = 0;
        for (Endpoint endpoint : endpoints) {
            sum += endpoint.weight;
        }
        this.totalWeight = sum;
        this.endpoints = endpoints;
    }
    @Override
    public final Iterator<Endpoint> iterator() {
        return ReadOnlyIterator.with(endpoints.iterator());
    }
    //省略其他代码
}
