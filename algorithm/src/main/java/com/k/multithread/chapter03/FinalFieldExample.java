package com.k.multithread.chapter03;

/**
 * final关键字的作用示例
 */
public class FinalFieldExample {
    final int x;
    int y;
    static FinalFieldExample instance;
    public FinalFieldExample() {
        x = 1;
        y = 2;
    }
    public static void writer() {
        instance = new FinalFieldExample();
    }
    public static void reader() {
        final FinalFieldExample theInstance = instance;
        if (theInstance != null) {
            int diff = theInstance.y - theInstance.x;
            //diff的值可能为1，也可能为-1
            System.out.println(diff);
        }
    }
    private static void print(int x) {
        //...
    }
}
