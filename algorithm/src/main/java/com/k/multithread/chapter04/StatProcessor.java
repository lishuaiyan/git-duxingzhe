package com.k.multithread.chapter04;

import java.util.Map;

public interface StatProcessor {
    void process(String record);
    Map<Long, DelayItem> getResult();
}
