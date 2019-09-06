package com.k.multithread.chapter01;


public class JavaThreadAnywhere {
    public static void main(String[] args) {
        //获取当前线程
        Thread currentThread = Thread.currentThread();
        //获取当前线程的名称
        String currentThreadName = currentThread.getName();

        System.out.printf("The main method was executed by thread:%s\n",currentThreadName);
        Helper helper = new Helper("Java Thread Anywhere");
        helper.run();
    }
    static class Helper implements Runnable {
        private final String message;
        public Helper(String message) {
            this.message = message;
        }
        private void doSomthing(String message) {
            //获取当前线程
            Thread currentThread = Thread.currentThread();
            //获取当前线程的名称
            String currentThreadName = currentThread.getName();
            System.out.printf("The doSomething method was executed by thread:%s\n", currentThreadName);
            System.out.println("Do Something with " + message);
        }
        @Override
        public void run() {
            doSomthing(message);
        }
    }
}
