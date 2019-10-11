package com.k.multithread.chapter07.quarter01;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配置管理器
 * 该类可能导致死锁！
 */
public enum  ConfigurationManager {
    INSTANCE;
    protected final Set<ConfigEventListener> listeners = new HashSet<ConfigEventListener>();
    public Configuration load(String name) {
        Configuration cfg = loadConfigurationFromDB(name);
        synchronized (this) {
            for (ConfigEventListener listener : listeners) {
                listener.onConfigLoaded(cfg);
            }
        }
        return cfg;
    }
    //从数据库加载配置实体（数据）
    private Configuration loadConfigurationFromDB(String name) {
        //...
        return null;
    }
    public synchronized void registerListener(ConfigEventListener listener) {
        listeners.add(listener);
    }
    public synchronized void removeListener(ConfigEventListener listener) {
        listeners.remove(listener);
    }
    public synchronized void update(String name, int newVersion, Map<String, String> properties) {
        for (ConfigEventListener listener : listeners) {
            //这个外部方法可能导致死锁
            listener.onConfigUpdated(name, newVersion, properties);
        }
    }
        }
