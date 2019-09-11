package com.k.multithread.chapter03;

public interface LoadBalancer {
    void updateCandidate(final Candidate candidate);
    Endpoint nextEndpoint();
}
