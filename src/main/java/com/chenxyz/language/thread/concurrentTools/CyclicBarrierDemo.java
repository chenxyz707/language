package com.chenxyz.language.thread.concurrentTools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class CyclicBarrierDemo {

    private static final int THREAD_NUM=10;
    private static CyclicBarrier cb = new CyclicBarrier(THREAD_NUM);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUM; i++) {
            System.out.println("create thread " + i);
            Thread thread = new Thread(new ThreadDemo(i));
            thread.start();
        }
    }

    static class ThreadDemo implements Runnable{
        private int a = 0;
        public ThreadDemo(int a) {
            this.a = a;
        }

        @Override
        public void run() {
            System.out.println("start thread " + a);
            try {
                cb.await(); //等待线程，直到满足一定条件被唤醒。
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("end thread " + a);//并发执行的任务
        }
    }
}
