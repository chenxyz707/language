package com.chenxyz.language.thread.volatiletest;

/**
 * volatile只保证了线程之间的可见性，不能保证线程安全
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class VolatileTest {

    public static void main(String[] args) {

        VolatileThread volatileThread = new VolatileThread();

        Thread t1 = new Thread(volatileThread);
        Thread t2 = new Thread(volatileThread);
        Thread t3 = new Thread(volatileThread);
        Thread t4 = new Thread(volatileThread);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
