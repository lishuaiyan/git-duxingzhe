package com.k.multithread.chapter04;

import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * 日志读取线程实现类
 */
public class LogReaderThread extends AbstractLogReader {
    //线程安全的队列
    final ArrayBlockingQueue<RecordSet> channel = new ArrayBlockingQueue<RecordSet>(2);

    public LogReaderThread(InputStream in, int)
}
