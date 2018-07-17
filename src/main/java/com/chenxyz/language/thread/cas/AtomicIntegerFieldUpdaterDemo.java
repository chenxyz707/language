package com.chenxyz.language.thread.cas;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 字段的原子更新
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-29
 */
public class AtomicIntegerFieldUpdaterDemo {

    class DemoData{
        public volatile int value1 = 1;
        volatile int value2 = 2;
        protected volatile int value3 = 3;
        private volatile int value4 = 4;

        @Override
        public String toString() {
            return "DemoData{" +
                    "value1=" + value1 +
                    ", value2=" + value2 +
                    ", value3=" + value3 +
                    ", value4=" + value4 +
                    '}';
        }
    }
    AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName) {
        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
    }
    void doit() {
        DemoData data = new DemoData();
        System.out.println("1 ==> "+getUpdater("value1").getAndSet(data, 10));
        System.out.println("3 ==> "+getUpdater("value2").incrementAndGet(data));
        System.out.println("2 ==> "+getUpdater("value3").decrementAndGet(data));
        //value3对于AtomicIntegerFieldUpdaterDemo是访问不到的，所以不能通过反射修改值
        //System.out.println("true ==> "+getUpdater("value4").compareAndSet(data, 4, 5));

        System.out.println(data.toString());
    }
    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        demo.doit();
    }
}
