package com.chenxyz.language.thread.concurrentTools;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class SemaphoreDemo<T> {

    private final Semaphore space;//同时只允许多少个线程访问资源
    private List queue = new LinkedList<>();

    public SemaphoreDemo(int spaceCounts) {
        this.space = new Semaphore(spaceCounts);
    }

    public void put(T x) throws InterruptedException {
        space.acquire();
        synchronized (queue) {
            System.out.println("put a item " + x);
            queue.add(x);
        }
    }

    public T take() {
        T t;
        synchronized (queue) {
            t = (T)queue.remove(0);
            System.out.println("take a item " + t);
        }
        space.release();
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo<String> demo = new SemaphoreDemo<>(2);
        demo.put("1");
        demo.put("2");
        demo.put("0");
        demo.take();
        demo.put("3");
        demo.take();
        demo.take();
    }
}
