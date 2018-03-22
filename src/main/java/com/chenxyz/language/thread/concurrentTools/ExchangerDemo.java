package com.chenxyz.language.thread.concurrentTools;

import java.util.concurrent.Exchanger;

/**
 * 两个线程交换数据
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        Thread thread1 = new Thread(new ExchangerThread(exchanger, "Message from Thread1"), "Thread1");
        Thread thread2 = new Thread(new ExchangerThread(exchanger, "Message from Thread2"), "Thread2");

        thread1.start();
        thread2.start();
    }

    static class ExchangerThread implements Runnable {

        private Exchanger<String> exchanger;

        private String message;

        public ExchangerThread(Exchanger<String> exchanger, String message) {
            this.exchanger = exchanger;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " : " + exchanger.exchange(message));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
