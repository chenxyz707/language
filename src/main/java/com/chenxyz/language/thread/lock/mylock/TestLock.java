package com.chenxyz.language.thread.lock.mylock;

import com.chenxyz.language.thread.utils.SleepUtils;

import java.util.concurrent.locks.Lock;

/**
 * 测试独占锁共享锁
 * 独占锁：可以看到同一时刻只有一个线程可以拿到这个锁（控制台打印出一个线程名称后隔一段时间才会打印下一个）
 * 共享锁：可以看到同一时刻只有两个线程可以拿到这个锁（控制台会连续打印出两个线程名称）
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-24
 */
public class TestLock {

    public static void main(String[] args) {
        TestLock testSLock = new TestLock();
        testSLock.test();
    }

    public void test() {
        //测试独占锁
        final Lock lock = new MutexLock();

        //测试共享锁
        //final Lock lock = new SharedLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                    SleepUtils.second(2);
                }
            }
        }

        // 启动10个子线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.setName("Thread "+i);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }

}
