package com.chenxyz.language.thread.concurrentTools;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * 如果说想在所有线程运行完成之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数：
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class CyclicBarrierCount {

    // 5个线程
    private static CyclicBarrier cb = new CyclicBarrier(5, new CountThread());

    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    /**
     * 在栅栏中的所有线程都执行到达屏障后， 执行的Runnable线程。
     * 选择一个线程执行
     **/
    private static class CountThread implements Runnable {
        @Override
        public void run() {
            int result = 0;
            for(Map.Entry<String, Integer> workmap : map.entrySet()) {
                result = result + workmap.getValue();
            }
            System.out.println("result : " + result);
        }
    }

    private static class WorkThread implements Runnable {

        private Random t = new Random();

        @Override
        public void run() {
            int r = t.nextInt(10);
            map.put(Thread.currentThread().getId()+"", r);
            System.out.println("map add " + r);
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new WorkThread());
            thread.start();
        }
    }
}
