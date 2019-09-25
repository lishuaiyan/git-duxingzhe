package com.k.multithread.chapter06.quarter03;



import com.k.multithread.util.ReadOnlyIterator;

import java.util.*;

/**
 * 减少不可表对象占用的内存空间
 */
public class BigImmutableObject implements Iterable<Map.Entry<String, BigObject>> {
    private final HashMap<String, BigObject> registory;

    public BigImmutableObject(HashMap<String, BigObject> registory) {
        this.registory = registory;
    }
    public BigImmutableObject(BigImmutableObject prototype, String key, BigObject newValue) {
        this(createRegistory(prototype, key, newValue));
    }
    @SuppressWarnings("unchecked")
    private static HashMap<String, BigObject> createRegistory (
        BigImmutableObject prototype, String key, BigObject newValue) {
        //从现有对象中复制（浅复制）字段
        HashMap<String, BigObject> newRegistory = (HashMap<String, BigObject>) prototype.registory.clone();
        //仅更新需要更新的部分
        newRegistory.put(key, newValue);
        return newRegistory;
    }

    @Override
    public Iterator<Map.Entry<String, BigObject>> iterator() {
        //对entrySet进行防御性复制
        final Set<Map.Entry<String, BigObject>> readOnlyEntries = Collections.unmodifiableSet(registory.entrySet());
        //返回一个只读的Iterator实例
        return ReadOnlyIterator.with(readOnlyEntries.iterator());
    }
    public BigObject getObject(String key) {
        return registory.get(key);
    }
    public BigImmutableObject update(String key, BigObject newValue) {
        return new BigImmutableObject(this, key, newValue);
    }
 }
class BigObject {
    //省略其他代码
}