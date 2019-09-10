package com.k.multithread.chapter02;

import com.k.multithread.util.stf.*;

/**
 * 再现JIT指令重排序的Demo
 */
@ConcurrencyTest(iterations = 20000000)
public class JITRecorderingDemo {
   private int externalData = 1;
   private Helper helper;

   @Actor
    public void createHelper() {
       helper = new Helper(externalData);
   }
   @Observer({
           @Expect(desc = "Helper is null", expected = -1),
           @Expect(desc = "Helper is not null , but it is not initiailized", expected = 0),
           @Expect(desc = "Only 1 field of Helper instance was initiailized", expected = 1),
           @Expect(desc = "Only 2 field of Helper instance was initiailized", expected = 2),
           @Expect(desc = "Only 3 field of Helper instance was initiailized", expected = 3),
           @Expect(desc = "Helper instance was fully initiailized", expected = 4)
   })
    public int consume() {
       int sum = 0;
       /**
        * 由于我们为对共享变量helper进行任何处理（比如采用volatitle关键字修饰），因此，这里可能存在可见性问题，即当前线程读取到的变量值可能为null。
        */
       final Helper observedHelper = helper;
       if (null == observedHelper) {
           sum = -1;
       } else {
           sum = observedHelper.payloadA + observedHelper.payloadB
                   + observedHelper.payloadC + observedHelper.payloadD;
       }
       return sum;
   }
   static class Helper {
       int payloadA;
       int payloadB;
       int payloadC;
       int payloadD;
       public Helper(int externalData) {
           this.payloadA = externalData;
           this.payloadB = externalData;
           this.payloadC = externalData;
           this.payloadD = externalData;
       }
   }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
            //调用测试工具运行测试代码
            TestRunner.runTest(JITRecorderingDemo.class);

    }
}
