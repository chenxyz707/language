package com.chenxyz.language.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 又返回值的ForkJoin需要继承{@link RecursiveTask}
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-31
 */
public class ForkJoinCompute extends RecursiveTask<Long> {

    // 设置一个最大计算容量
    private final int DEFAULT_CAPACITY = 10000000;

    // 表示计算的范围
    private int start;

    private int end;

    public ForkJoinCompute(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start < DEFAULT_CAPACITY) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int mid = (start + end)/2;
            ForkJoinCompute left = new ForkJoinCompute(start, mid);
            ForkJoinCompute right = new ForkJoinCompute(mid, end);

            invokeAll(left, right);

            sum = left.join() + right.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinCompute fj = new ForkJoinCompute(0, 1000000000);
        long result = fjp.invoke(fj);
        long time2 = System.currentTimeMillis();
        System.out.println("fork join");
        System.out.println(result);
        System.out.println(time2-time1);

        long time3 = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum += i;
        }
        long time4 = System.currentTimeMillis();
        System.out.println("single thread");
        System.out.println(sum);
        System.out.println(time4-time3);
    }
}
