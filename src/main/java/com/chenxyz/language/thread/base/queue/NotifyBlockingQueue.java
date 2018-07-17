package com.chenxyz.language.thread.base.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用wait和notify实现的一个有界阻塞队列
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-16
 */
public class NotifyBlockingQueue<T> {

    private List queue = new LinkedList<>();
    private final int limit;

    public NotifyBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while(this.queue.size() == this.limit) {
            wait();
        }
        this.queue.add(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while(this.queue.size() == 0) {
            wait();
        }
        T item = (T)this.queue.remove(0);
        notifyAll();
        return item;
    }
}
