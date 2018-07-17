package com.chenxyz.language.thread.base.interrupt;

/**
 * 使用自定义的取消标志位中断线程
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-16
 */
public class FlagCancel {

    private static class CancelRunnable implements Runnable {

        private volatile boolean on = true;
        private long i = 0;

        @Override
        public void run() {
            while(on) {
                System.out.println(i++);

                //阻塞方法，on不起作用
                //wait,sleep,blockingqueue(put,take)
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel() {
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CancelRunnable runnable = new CancelRunnable();
        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(10);
        runnable.cancel();
    }
}
