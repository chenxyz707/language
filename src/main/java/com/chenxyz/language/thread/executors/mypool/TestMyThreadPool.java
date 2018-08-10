package com.chenxyz.language.thread.executors.mypool;

import java.util.Random;

/**
 * 查看线程id 被复用
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-03
 */
public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // 创建3个线程的线程池
        MyThreadPool t = new MyThreadPool(3);
        t.execute(new MyTask("task1"));
        t.execute(new MyTask("task2"));
        t.execute(new MyTask("task3"));
        t.execute(new MyTask("task4"));
        t.execute(new MyTask("task5"));

        Thread.sleep(1000);
        System.out.println(t);
        Thread.sleep(90000);
        t.destroy();// 所有线程都执行完成才destory
        System.out.println(t);
    }


    // 任务类
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
            System.out.println("my task 初始化完成！！");
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
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
