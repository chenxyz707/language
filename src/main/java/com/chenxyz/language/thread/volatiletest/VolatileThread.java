package com.chenxyz.language.thread.volatiletest;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class VolatileThread implements Runnable {

    private volatile int a = 0;

    @Override
    public void run() {
        synchronized (this) {
            a = a+1;
            System.out.println(Thread.currentThread().getName()+"----"+a);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a=a+1;
            System.out.println(Thread.currentThread().getName()+"----"+a);
        }
    }
}
