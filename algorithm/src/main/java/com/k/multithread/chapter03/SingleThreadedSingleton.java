package com.k.multithread.chapter03;

public class SingleThreadedSingleton {
    //保存该类的唯一实例
    private static SingleThreadedSingleton instance = null;
    /**
     * 私有构造器使其他类无法直接通过new创建该类的实例
     */
    private SingleThreadedSingleton() {
        //什么也不做
    }
    /**
     * 创建并返回该类的唯一实例
     * 即只有该方法被调用时该类的唯一实例才会被创建
     * @return
     */
    public static SingleThreadedSingleton getInstance() {
        if (null == instance) {//操作 1
            instance = new SingleThreadedSingleton();//操作 2
        }
        return instance;
    }
    public void someService() {
        //省略其他代码
    }
}
