package com.k.multithread.chapter03;

import com.k.multithread.util.Debug;

/**
 * 基于枚举类型的单例模式实现代码
 */
public class EnumBasedSingletonExample {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                Debug.info(Singleton.class.getName());
                Singleton.INSTANCE.someService();
            }
        };
        t.start();
    }
    public static enum Singleton {
        INSTANCE;
        //私有构造器
        Singleton() {
            Debug.info("Singleton inited.");
        }
        public void someService() {
            Debug.info("someService invoked.");
            //省略其他代码
        }
    }
}
