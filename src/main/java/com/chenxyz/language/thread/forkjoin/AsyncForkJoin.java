package com.chenxyz.language.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 异步的ForkJoin
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-31
 */
public class AsyncForkJoin extends RecursiveAction{

    // 设置一个最大计算容量
    private final int DEFAULT_CAPACITY = 10000000;

    // 表示计算的范围
    private int start;

    private int end;

    public AsyncForkJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        System.out.println("compute!!");
        long sum = 0;
        if (end - start < DEFAULT_CAPACITY) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            int mid = (start + end)/2;
            AsyncForkJoin left = new AsyncForkJoin(start, mid);
            AsyncForkJoin right = new AsyncForkJoin(mid, end);

            invokeAll(left, right);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        AsyncForkJoin asyncForkJoin = new AsyncForkJoin(0, 1000000000);
        pool.execute(asyncForkJoin);
        while(!asyncForkJoin.isDone()) {
            System.out.println("is computing !!");
            TimeUnit.MILLISECONDS.sleep(50);
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);

        asyncForkJoin.join();

    }
}
