package com.k.multithread.chapter07.quarter01;


import java.util.Map;

public interface ConfigEventListener {
    void onConfigLoaded(Configuration cfg);

    void onConfigUpdated(String name, int newVersion, Map<String, String> properties);
}
