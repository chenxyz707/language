package com.chenxyz.language.thread.executors.mypool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-03
 */
public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        // 创建3个线程的线程池
        MyThreadPool t = new MyThreadPool(3);
        t.execute(new MyTask("task1", cdl));
        t.execute(new MyTask("task2", cdl));
        t.execute(new MyTask("task3", cdl));
        t.execute(new MyTask("task4", cdl));
        t.execute(new MyTask("task5", cdl));

//        Thread.sleep(1000);
        System.out.println(t);
        cdl.countDown();
        Thread.sleep(90000);
        t.destroy();// 所有线程都执行完成才destory
        System.out.println(t);
    }


    // 任务类
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();
        private CountDownLatch cdl;

        public MyTask(String name, CountDownLatch cdl) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public CountDownLatch getCdl() {
            return cdl;
        }

        public void setCdl(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {// 执行任务
            try {
                cdl.await();
                Thread.sleep(r.nextInt(1000)+2000);
                System.out.println(Thread.currentThread().getId()+" is executing!!!");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+" sleep InterruptedException:"
                        +Thread.currentThread().isInterrupted());
            }
            System.out.println("任务 " + name + " 完成");
        }
    }
}
