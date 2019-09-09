package com.k.multithread.chapter02;

import com.k.multithread.util.Tools;

public class RaceConditionDemo {
    public static void main(String[] args) {
        //客户端线程数量
        int numberOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime.getRuntime().availableProcessors();
        Thread[] workerThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workerThreads[i] = new WorkerThread(i, 10);
        }
        //待所有线程创建完毕之后，再一次性将其启动，以便这些线程能够尽可能地在同一时间内运行
        for (Thread ct : workerThreads) {
            ct.start();
        }
    }
    //模拟业务流程
    static class WorkerThread extends Thread {
        private final int requestCount;
        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }
        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGenerator = RequestIDGenerator.getInstance();
            while (i-- > 0) {
                //生成RequestID
                requestID = requestIDGenerator.nextID();
                processRequest(requestID);
            }
        }
        //模拟请求处理
        private void processRequest(String requestID) {
            //模拟请求处理耗时
            Tools.randomPause(50);
            System.out.printf("%s got requestID: %s %n", Thread.currentThread().getName(), requestID);
        }
    }
}
