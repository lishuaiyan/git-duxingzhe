package com.k.multithread.chapter02;

import com.k.multithread.util.Tools;

/**
 * 可见性问题Demo
 */
public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(new TimeConsumingTask());
        thread.start();
        Thread.sleep(10000);
        timeConsumingTask.cancel();
    }

}
class TimeConsumingTask implements Runnable {
    private boolean toCancel = false;
    @Override
    public void run() {
        while (!toCancel) {
            if (doExecute()) {
                break;
            }
        }
        if (toCancel) {
            System.out.println("Task was canceled.");
        } else {
            System.out.println("Task done.");
        }
    }
    private boolean doExecute() {
        boolean isDone = false;
        System.out.println("executing ...");
        //模拟实际操作时间消耗
        Tools.randomPause(50);
        //省略其他代码
        return isDone;
    }
    public void cancel() {
        toCancel = true;
        System.out.println(this + "canceled.");
    }
}
