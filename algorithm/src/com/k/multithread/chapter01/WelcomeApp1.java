package com.k.multithread.chapter01;

public class WelcomeApp1 {
    public static void main(String[] args) {
        //创建线程
        Thread welcomeThread = new Thread(new WelcomeTask());
        //i启动线程
        welcomeThread.start();
        //输出当前线程的线程名称
        System.out.printf("1.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }
}
class WelcomeTask implements Runnable {
    //在该方法中实现线程的任务处理逻辑
    @Override
    public void run() {
        //输出当前线程的名称
        System.out.printf("2.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }
}
