package com.chenxyz.language.thread.lock.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock和Condition实现的有界阻塞队列
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-18
 */
public class LockBlockingQueue<T> {

    private List<T> queue = new LinkedList();

    private int limit;

    private Lock lock = new ReentrantLock();

    private Condition full = lock.newCondition();

    private Condition empty = lock.newCondition();

    public LockBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == limit) {
                full.await();
            }
            queue.add(item);
            empty.signal();
        } finally {
            lock.unlock();
        }

    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                empty.await();
            }
            T item = queue.remove(0);
            full.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
