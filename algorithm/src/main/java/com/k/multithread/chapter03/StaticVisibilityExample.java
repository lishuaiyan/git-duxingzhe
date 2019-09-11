package com.k.multithread.chapter03;

import com.k.multithread.util.Debug;
import com.k.multithread.util.Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * static 关键字可见性保障示例
 */
public class StaticVisibilityExample {
    private static Map<String, String> taskConfig;
    static {
        Debug.info("The class being initialized...");
        taskConfig = new HashMap<>();//语句 1
        taskConfig.put("url","https://github.com/Viscent");//语句 2
        taskConfig.put("timeout", "1000"); //语句 3
    }
    public static void changeConfig(String url, int timeout) {
        taskConfig = new HashMap<>();//语句 4
        taskConfig.put("url",url);//语句 5
        taskConfig.put("timeout",String.valueOf(timeout));//语句 6

    }
    public static void init() {
        //该线程至少能够看到语句 1~3的操作结果，而能否看到语句 4~6 的操作结果是没有保障的
        Thread t = new Thread() {
            @Override
            public void run() {
                String url = taskConfig.get("url");
                String timeout = taskConfig.get("timeout");
                doTask(url, Integer.parseInt(timeout));
            }
        };
        t.start();
    }
    private static void doTask(String url, int timeout) {
        //省略其他代码
        //模拟实际操作的耗时
        Tools.randomPause(500);
    }
}
