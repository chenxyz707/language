package com.chenxyz.language.thread.lock.queue;

/**
 * 队列测试
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-18
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        LockBlockingQueue queue = new LockBlockingQueue(10);
        Thread push = new ThreadPush(queue);
        push.setName("push");
        Thread pop = new ThreadPop(queue);
        pop.setName("pop");
        push.start();
        Thread.sleep(10000);
        pop.start();
    }


    //推数据入队列
    private static class ThreadPush extends Thread{
        LockBlockingQueue<Integer> queue;

        public ThreadPush(LockBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int i = 20;
            while(i>0){
                try {
                    Thread.sleep(500);
                    System.out.println(" i="+i+" will push");
                    queue.enqueue(i--);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
        }
    }

    //取数据出队列
    private static class ThreadPop extends Thread{
        LockBlockingQueue<Integer> bq;

        public ThreadPop(LockBlockingQueue<Integer> bq) {
            this.bq = bq;
        }
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println(Thread.currentThread().getName()
                            +" will pop.....");
                    Integer i = bq.dequeue();
                    System.out.println(" i="+i.intValue()+" alread pop");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }

        }
    }
}
