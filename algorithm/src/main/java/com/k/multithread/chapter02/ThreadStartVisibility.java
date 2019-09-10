package com.k.multithread.chapter02;

import com.k.multithread.util.Tools;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程启动与可见性
 */
public class ThreadStartVisibility {
    //线程间的共享变量
    static int data = 0;
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                //使当前线程休眠R毫秒（R的值为随机值）
                Tools.randomPause(50);
                //读取并打印变量data的值
                System.out.println(data);
            }
        };
        //在子线程thread启动前更新变量data的值
        data = 1; //语句 1
        thread.start();
        //使当前线程休眠R秒（R的值为随机数）
        Tools.randomPause(50);
        //在子线程thread启动后更新变量data的值
        data = 2; //语句 2


    }
}
