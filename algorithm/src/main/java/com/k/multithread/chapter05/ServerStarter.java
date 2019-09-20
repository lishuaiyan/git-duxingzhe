package com.k.multithread.chapter05;

/**
 * 服务器启动代码
 */
public class ServerStarter {
    public static void main(String[] args) {
        //省略其他代码
        //启动所有服务
        ServiceManager.startService();
        //执行其他操作
        //在所有其他操作执行结束后检测服务启动状态
        boolean allIsOk;
        //检测全部服务的启动状态
        allIsOk = ServiceManager.checkServiceStatus();

        if (allIsOk) {
            System.out.println("All services were successfully stared!");
            //省略其他代码
        } else {
            //个别服务启动失败，退出JVM
            System.err.println("Some service(s) fialed to start, exiting JVM... ");
            System.exit(1);
        }
        //...
    }
}
