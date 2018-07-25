package com.chenxyz.language.thread.concurrentTools;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-25
 */
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static final int count = 5000;
    private static int num = 2;
    private static CountDownLatch cdl = new CountDownLatch(num);

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(5);
        ConcurrentLinkedQueueTest.offer();

        //两个线程取数据
        for (int i = 0; i < num; i++) {
            es.submit(new Poll());
        }
        //等待两个子线程结束
        cdl.await();
        long end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - begin) + "ms");
        es.shutdown();
    }


    public static void offer() {
        for (int i = 0; i < count; i++) {
            queue.offer(i);
        }
    }

    static class Poll implements Runnable {

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            cdl.countDown();
        }
    }
}
