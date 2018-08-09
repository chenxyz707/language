package com.chenxyz.language.thread.executors.future;

import java.util.concurrent.Callable;

/**
 * Callable是有返回值的
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-08
 */
public class ComputeTask implements Callable<Integer> {

    private Integer result = 0;
    private String taskName;

    public ComputeTask(Integer result, String taskName) {
        this.result = result;
        this.taskName = taskName;
        System.out.println(taskName+" 子任务初始化完成");
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(taskName+" 子任务已经创建");
        for (int i = 0; i < 100; i++) {
            result += i;
        }
        Thread.sleep(2000);
        System.out.println(taskName+" 子任务已经完成");
        return result;
    }
}
