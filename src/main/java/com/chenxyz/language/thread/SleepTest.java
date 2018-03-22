package com.chenxyz.language.thread;

/**
 * sleep方法是否会释放锁
 * 最主要的是sleep方法调用之后，并没有释放锁。使得线程仍然可以同步控制。sleep不会让出系统资源；
 * 而wait是进入线程等待池中等待，让出系统资源。
 *
 * 调用wait方法的线程，不会自己唤醒，需要线程调用 notify / notifyAll 方法唤醒等待池中的所有线程，
 * 才会进入就绪队列中等待系统分配资源。
 * sleep方法会自动唤醒，如果时间不到，想要唤醒，可以使用interrupt方法强行打断。
 *
 * sleep可以在任何地方使用。而wait，notify，notifyAll只能在同步控制方法或者同步控制块中使用。
 *
 * @author chenlinchao
 * @version 1.0
 * @date 2018-03-20
 */
public class SleepTest {

    //锁
    private Object lock = new Object();

    public static void main(String[] args) {
        SleepTest sleepTest = new SleepTest();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();
        try {
            Thread.sleep(1000);
            System.out.println("RunTest slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

    private class ThreadSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock");
            try {

                //拿到锁以后，休眠
                synchronized(lock) {
                    System.out.println(threadName+" taking the lock");
//                    Thread.sleep(5000);
                    lock.wait(5000);
                    System.out.println("Finish the work: "+threadName);
                }

            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock time="+System.currentTimeMillis());
            //拿到锁以后不休眠
            synchronized(lock) {
                System.out.println(threadName+" taking the lock time="+System.currentTimeMillis());
                System.out.println("Finish the work: "+threadName);
            }
        }
    }
}
