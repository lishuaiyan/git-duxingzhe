package com.k.multithread.chapter06.quarter02;

import com.k.multithread.chapter03.Endpoint;
import com.k.multithread.util.Debug;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 对服务器节点进行排序
 */
public class EndpointView {
    static final Comparator<Endpoint> DEFAULT_COMPARATOR;
    static {
        DEFAULT_COMPARATOR = new DefaultEndpointComparator();
    }
    //省略其他代码
    public Endpoint[] retrieveServerList(Comparator<Endpoint> comparator) {
        Endpoint[] serverList = doRetrieveServerList();
        Arrays.sort(serverList, comparator);
        return serverList;
    }
    public Endpoint[] retrieveServerList() {
        return retrieveServerList(DEFAULT_COMPARATOR);
    }

    private Endpoint[] doRetrieveServerList() {
        //...
        return new Endpoint[]{};
    }

    public static void main(String[] args) {
        EndpointView endpointView = new EndpointView();
        Endpoint[] serverList = endpointView.retrieveServerList();
        Debug.info(Arrays.toString(serverList));

    }
}
