package com.chenxyz.language.thread.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用stamped标记的更新方式
 * 因为t2线程使用的是原始的stamp所以会更新失败
 *
 * @author chexyz
 * @version 1.0
 * @date 2018-07-18
 */
public class AtomicStampedReferenceTest {

    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) throws InterruptedException {
        final int stamp = reference.getStamp();
        final Integer ref = reference.getReference();
        System.out.println("reference :" + ref +", stamp : " + stamp);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": reference:" + ref + ",stamp:" + stamp + ":"
                        +reference.compareAndSet(ref, ref+10, stamp, stamp+1));
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer ref = reference.getReference();
                System.out.println(Thread.currentThread().getName() + ": reference:" + ref + ",stamp:" + stamp + ":"
                        +reference.compareAndSet(ref, ref+10, stamp, stamp+1));
            }
        }, "t2");

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        System.out.println("reference :" + reference.getReference() +", stamp : " + reference.getStamp());
    }
}
