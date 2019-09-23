package com.k.multithread.chapter05.quarter05;

/**
 * 对传输通道的抽象
 * @param <P> 产品类型
 */
public interface Channel<P> {
    /**
     * 往传输通道存入一个产品
     */
    void put(P product) throws InterruptedException;

    /**
     * 从传输通道中取出一个产品
     * @return
     * @throws InterruptedException
     */
    P take() throws InterruptedException;
}
