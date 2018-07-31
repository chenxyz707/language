package com.chenxyz.language.thread.concurrentTools.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-31
 */
public class PutInCache implements Runnable {

    private DelayQueue<CacheBean<User>> queue;

    public PutInCache(DelayQueue<CacheBean<User>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        CacheBean cacheBean = new CacheBean("1","5秒",
                new User("1", "Tom"),5000);
        CacheBean cacheBean2 = new CacheBean("1","3秒",
                new User("2", "Jack"),3000);
        queue.offer(cacheBean);
        System.out.println("put in cache:"+cacheBean.getId()+":"+cacheBean.getName());
        queue.offer(cacheBean2);
        System.out.println("put in cache:"+cacheBean2.getId()+":"+cacheBean2.getName());
    }
}
