package com.k.multithread.chapter06.quarter05;

import java.util.*;

/**
 * 保障对外包装对象的遍历操作的线程安全
 */
public class SyncCollectionSafeTraversal {
    final List<String> syncList = Collections.synchronizedList(new ArrayList<>());
    //...
    public void dump() {
        Iterator<String> iterator = syncList.iterator();
        synchronized (syncList) {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
