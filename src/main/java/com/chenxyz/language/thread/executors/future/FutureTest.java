package com.chenxyz.language.thread.executors.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-09
 */
public class FutureTest {

    public static void main(String[] args) {
        //FutureTest.method1();
        FutureTest.method2();

    }

    public static void method1() {
        List<FutureTask<Integer>> taskList = new ArrayList<>();

        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象创建FutureTask对象
            FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(i, "task_"+i));
            taskList.add(ft);
            // ExecutorSerivce接收一个Runnable接口
            exec.submit(ft);
        }
        System.out.println("所有任务已提交");

        // 开始统计各个计算线程的结果
        int totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                // FutureTask的get方法会自动阻塞，知道获取计算结果为止
                totalResult += ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("计算完成, total : " + totalResult);
        exec.shutdown();
    }

    public static void method2() {
        List<Future<Integer>> futureList = new ArrayList<>();

        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // ExecutorSerivce接收一个Callable直接返回Future
            Future<Integer> future = exec.submit(new ComputeTask(i, "task_" + i));
            futureList.add(future);
        }
        System.out.println("所有任务已提交");

        // 开始统计各个计算线程的结果
        int totalResult = 0;
        for (Future<Integer> ft : futureList) {
            try {
                // FutureTask的get方法会自动阻塞，知道获取计算结果为止
                totalResult += ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("计算完成, total : " + totalResult);
        exec.shutdown();
    }
}
