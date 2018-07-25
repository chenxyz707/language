package com.chenxyz.language.thread.base.join;

/**
 * 在线程A中执行了thread.join()
 * 线程A后面的代码会等待thread线程执行完成之后继续执行
 *
 * 这个案例中，线程会完全按照0,1,2,3,4...的顺序执行
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-17
 */
public class JoinTest {

    static class CutInLine implements Runnable{

        private Thread thread;

        public CutInLine(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                //在被插队的线程里，调用一下插队线程的join方法
                thread.join();
                Thread.sleep((long) (1000* Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" will work");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread thread =
                    new Thread(new CutInLine(previous),String.valueOf(i));
            System.out.println(previous.getName() + " cut in the thread:"+thread.getName());
            thread.start();
            previous = thread;
        }

    }

}
