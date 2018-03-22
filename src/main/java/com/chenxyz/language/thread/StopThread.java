package com.chenxyz.language.thread;

/**
 * 停止线程
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class StopThread {

    public static void main(String[] args) {

        ThreadDemo target = new ThreadDemo();
        target.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        target.cancel();
    }

    static class ThreadDemo extends Thread {

        private static volatile boolean stop = false;
        private int i=0;

        @Override
        public void run() {
            System.out.println("进入线程........"+Thread.currentThread().isInterrupted());
            while(!stop && !Thread.currentThread().isInterrupted()) {
                i++;
                try {
                    synchronized (this) {
                        wait();// 阻塞了
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(i > 100) {
                    break;
                }
            }
            System.out.println("结束线程........--->i="+i);
        }

        public void cancel() {
            stop = true;
            interrupt();
        }
    }
}
