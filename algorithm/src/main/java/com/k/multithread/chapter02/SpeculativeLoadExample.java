package com.k.multithread.chapter02;

/**
 * 猜测执行实例代码
 */
public class SpeculativeLoadExample {
    private boolean ready = false;
    private int[] data = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

    public void writer() {
        int[] newData = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < newData.length; i++) {
            //此处包含读内存操作
            newData[i] = newData[i] - 1;
        }
        data = newData;// 语句 1
        //此处包含写内存操作
        ready = true;
    }

    public int reader() {
        int sum = 0;
        int[] snapshot;
        if (ready) { //语句 3 （if语句）
            snapshot = data;
            for (int i = 0; i < snapshot.length; i++) { //语句 4 (for循环语句)
                sum += snapshot[i]; //语句 5
            }
        }
        return sum;
    }
}
