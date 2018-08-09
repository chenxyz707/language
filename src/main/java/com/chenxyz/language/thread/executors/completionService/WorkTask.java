package com.chenxyz.language.thread.executors.completionService;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-08-07
 */
public class WorkTask implements Callable<String> {

    private String name;

    public WorkTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        //休眠随机时间，观察获取结果的行为。
        int sleepTime = new Random().nextInt(1000);
        Thread.sleep(sleepTime);
        String str = name+" sleept time:"+sleepTime;
        System.out.println(str+" finished....");
        return str;
    }
}
