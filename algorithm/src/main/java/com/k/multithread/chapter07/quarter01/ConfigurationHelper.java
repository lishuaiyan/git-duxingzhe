package com.k.multithread.chapter07.quarter01;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 配置助手
 * 该类可能导致死锁
 */
public enum ConfigurationHelper implements ConfigEventListener {
    INSTANCE;
    final ConfigurationManager configurationManager;
    final ConcurrentMap<String, Configuration> cachedConfig;

    private ConfigurationHelper() {
        configurationManager = ConfigurationManager.INSTANCE;
        cachedConfig = new ConcurrentHashMap<String, Configuration>();
    }
public Configuration getConfig(String name) {
        Configuration cfg;
        cfg = getCachedConfig(name);
        if (null == cfg) {
            synchronized (this) {
                cfg = getCachedConfig(name);
                if (null == cfg) {
                    cfg = configurationManager.load(name);
                    cachedConfig.put(name, cfg);
                }
            }
        }
        return cfg;
}
public Configuration getCachedConfig(String name) {
        return cachedConfig.get(name);
}
public ConfigurationHelper init() {
        configurationManager.registerListener(this);
        return this;
    }
    @Override
    public void onConfigLoaded(Configuration cfg) {
        cachedConfig.putIfAbsent(cfg.getName(), cfg);
    }
    @Override
    public void onConfigUpdated(String name, int newVersion, Map<String, String> properties) {
        Configuration cachedConfig = getCachedConfig(name);
        //更新内容和版本这个两个操作必须是原子操作
        synchronized (this) {
            if (null != cachedConfig) {
                cachedConfig.update(properties);
                cachedConfig.setVersion(newVersion);
            }
        }
    }
}
