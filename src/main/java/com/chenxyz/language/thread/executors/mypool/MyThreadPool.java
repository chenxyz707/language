package com.chenxyz.language.thread.executors.mypool;

import java.util.LinkedList;
import java.util.List;

/**
 * 我的线程池
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-03
 */
public class MyThreadPool {

    private int poolSize = 5;

    private Worker[] workers;

    private List<Runnable> tasks = new LinkedList<>();

    public MyThreadPool(int poolSize) {
        this.poolSize = poolSize;
        workers = new Worker[poolSize];
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notifyAll();
        }
    }

    public void destroy() {
        for (int i = 0; i < poolSize; i++) {
            workers[i].stopWorker();
            workers[i] = null;
        }
        tasks.clear();
    }

    private class Worker extends Thread {

        private volatile boolean on = true;

        @Override
        public void run() {
            Runnable runnable = null;
            try {
                while (on && !isInterrupted()) {
                    synchronized (tasks) {
                        //任务队列中无任务，工作线程等待
                        while (on && !isInterrupted() && tasks.isEmpty()) {
                            tasks.wait(1000);
                        }
                        //任务队列中有任务时进行处理
                        if (on && !isInterrupted() && !tasks.isEmpty()) {
                            runnable = tasks.remove(0);
                        }
                        if (runnable != null) {
                            System.out.println(getId()+" ready execute");
                            // 调用run方法在该线程中运行
                            runnable.run();
                        }
                        runnable = null;
                    }
                }
            } catch(InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + " is interrupeted");
            }
        }

        public void stopWorker() {
            on = false;
            interrupt();
        }
    }
}
