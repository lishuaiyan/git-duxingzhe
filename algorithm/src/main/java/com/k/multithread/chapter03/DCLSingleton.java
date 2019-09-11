package com.k.multithread.chapter03;

/**
 * 基于双重检查锁定的正确单例模式实现
 */
public class DCLSingleton {
    /**
     * 保存该类的唯一实例，使用volatile关键字修饰instance
     */
    private static volatile DCLSingleton instance;
    /**
     * 私有构造器事其他类无法直接通过nwe创建该类的实例
     */
    private DCLSingleton() {
        //什么也不做
    }
    /**
     * 创建并返回该类的唯一实例
     * 即只有该方法被调用时该类的唯一实例才会被创建
     * @return
     */
    public static DCLSingleton getInstance() {
        if (null == instance) {//操作 1 第一次检查
            synchronized (DCLSingleton.class) {
                if (null == instance) {//操作2 第二次检查
                    instance = new DCLSingleton();//操作 3
                }
            }
        }
        return instance;
    }
    public void someService() {
        //省略其他代码
    }
}
