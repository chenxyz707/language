package com.chenxyz.language.thread.executors.completionService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 查看输出结果可以发现CompletionService使得最先处理完的任务优先返回
 * 而普通线程池则会阻塞返回结果，不论后面的结果是否已经计算完成，必须等待前面的结果返回后才会返回后面的结果
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-07
 */
public class CompletionTest {

    private int POOL_SIZE = 10;
    private int TOTAL_TASK = 10;

    // 方法一：从固定大小的线程池中取返回结果
    public void testByQueque() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<>();

        // 存放任务Future
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = pool.submit(new WorkTask("task" + i));
            queue.add(future);
        }

        //输出任务结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            System.out.println("Execute Task:" + queue.take().get());
        }
        pool.shutdown();
    }
    public void testByCompletion() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<String> service = new ExecutorCompletionService<String>(pool);

        //提交任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            service.submit(new WorkTask("task" + i));
        }

        //输出任务结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = service.take();
            System.out.println("CompletionService:" + future.get());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionTest completionTest = new CompletionTest();
//        completionTest.testByQueque();
        completionTest.testByCompletion();
    }
}
