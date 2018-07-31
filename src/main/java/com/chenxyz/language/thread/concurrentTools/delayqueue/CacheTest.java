package com.chenxyz.language.thread.concurrentTools.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * 测试延时队列 只有时间到了 数据才能从队列中取出来
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-31
 */
public class CacheTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<CacheBean<User>> queue = new DelayQueue<>();
        new Thread(new PutInCache(queue)).start();
        new Thread(new GetFromCache(queue)).start();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
