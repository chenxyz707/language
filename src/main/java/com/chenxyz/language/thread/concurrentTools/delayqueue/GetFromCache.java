package com.chenxyz.language.thread.concurrentTools.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * 从缓存中取数据
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-31
 */
public class GetFromCache implements Runnable {

    private DelayQueue<CacheBean<User>> queue;

    public GetFromCache(DelayQueue<CacheBean<User>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                CacheBean<User> cacheBean = queue.take();
                System.out.println("GetFromCache"+cacheBean.getId()+":"+cacheBean.getName()+
                        "data:"+((User)cacheBean.getData()).getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
