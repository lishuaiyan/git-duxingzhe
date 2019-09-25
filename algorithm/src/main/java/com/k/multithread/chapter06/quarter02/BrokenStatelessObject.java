package com.k.multithread.chapter06.quarter02;

import java.util.HashMap;
import java.util.Map;

/**
 * 多个线程访问本身不包含状态的对象也可能存在共享状态示例
 */
public class BrokenStatelessObject {
    public String doSomething(String s) {
        UnsafeSingleton us = UnsafeSingleton.INSTANCE;
        int i = us.doSomething(s);
        UnsafeStatefullObject sfo = new UnsafeStatefullObject();
        String str = sfo.doSomething(s,i);
        return str;
    }

    public String doSomething1(String s) {
        UnsafeSingleton us = UnsafeSingleton.INSTANCE;
        UnsafeStatefullObject sfo = new UnsafeStatefullObject();
        String str;
        synchronized (this) {
            str = sfo.doSomething(s, us.doSomething(s));
        }
        return str;
    }
}
class UnsafeStatefullObject {
    static Map<String, String> cache = new HashMap<>();

    public String doSomething(String s, int len) {
        String result = cache.get(s);
        if (null == result) {
            result = md5sum(result, len);
        }
        cache.put(s, result);
        return result;
    }

    private String md5sum(String result, int len) {
        return null;
    }
}

enum UnsafeSingleton {
    INSTANCE;
    public int doSomething(String s) {
        //省略其他代码
        //访问state1
        return 0;
    }
}