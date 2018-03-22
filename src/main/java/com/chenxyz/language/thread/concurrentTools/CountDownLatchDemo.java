package com.chenxyz.language.thread.concurrentTools;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch计数器，每次调用数量-1，当数值为0时，唤醒所有等待的线程。
 *
 * CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
 * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class CountDownLatchDemo {

    private static final int THREAD_NUM = 10;
    private static CountDownLatch cdl = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) {
        for (int i=0; i<THREAD_NUM; i++) {
            Thread thread = new Thread(new ThreadDemo(i));
            thread.start();
            cdl.countDown();
        }

    }

    static class ThreadDemo implements Runnable{

        private int a = 0;

        public ThreadDemo(int a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                cdl.await();//等待线程，直到满足一定条件被唤醒。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(a);//并发执行的任务
        }
    }
}
