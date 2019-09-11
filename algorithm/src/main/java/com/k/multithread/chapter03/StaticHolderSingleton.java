package com.k.multithread.chapter03;

import com.k.multithread.util.Debug;

/**
 * 基于静态内部类的单例模式
 */
public class StaticHolderSingleton {
    //私有构造器
    private StaticHolderSingleton() {
        Debug.info("StaticHolderSingleton inited.");
    }
    private static class InstanceHolder {
        //保存外部类的唯一实例
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }
    public static StaticHolderSingleton getInstance() {
        Debug.info("getInstance invoked.");
        return InstanceHolder.INSTANCE;
    }
    public void someService() {
        Debug.info("someService invoked.");
    }

    public static void main(String[] args) {
        StaticHolderSingleton.getInstance().someService();
    }
}
