package com.chenxyz.language.thread.lock;

import com.chenxyz.language.thread.SleepUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的使用示例
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class ReadWriteLockDemo {

    static int THREAD_NUM = 5;
    static CountDownLatch cdl = new CountDownLatch(THREAD_NUM);

    static final Map<String, String> map = new HashMap<String, String>();
    static ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
    static Lock r = rrwl.readLock(); //从读写锁中获得读锁
    static Lock w = rrwl.writeLock();//从读写锁中获得写锁

    public void put(String key, String value){
        w.lock();
        try{
            map.put(key, value);
        }finally{
            w.unlock();
        }
    }

    public String get(String key){
        r.lock();
        try{
            return map.get(key);
        }finally{
            r.unlock();
        }
    }

    static class RWThread implements Runnable {

        private String value;

        public RWThread(String value) {
            this.value = value;
        }

        @Override
        public void run() {
            try {
                System.out.println(value + " is waiting!");
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("set " + value + ",  " + i);
                map.put(value+i, value+i);
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new RWThread(i + "")).start();
            cdl.countDown();
        }
        SleepUtils.second(5);
        System.out.println(map);
    }
}
