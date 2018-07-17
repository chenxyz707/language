package com.chenxyz.language.thread.base.interrupt;

/**
 * 调用阻塞方法时如何中断线程
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-16
 */
public class BlockInterrupt {

    private static Object o = new Object();


    /*while循环中包含try/catch块*/
    private static class WhileTryWhenBlock extends Thread {
        private volatile boolean on = true;
        private long i =0;
        @Override
        public void run() {
            System.out.println("当前执行线程id："+Thread.currentThread().getId());
            while (on && !Thread.currentThread().isInterrupted()) {
                System.out.println("i="+i++);
                try {

                    //抛出中断异常的阻塞方法，抛出异常后，中断标志位会改成false
                    //可以理解为这些方法会隐含调用Thread.interrputed()方法
                    synchronized (o){
                        o.wait();
                    }

                } catch (InterruptedException e) {
                    System.out.println("当前执行线程的中断标志位："
                            +Thread.currentThread().getId()
                            +":"+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();//重新设置一下 调用之后线程的终端标志位才会变为false
                    System.out.println("当前执行线程的中断标志位："
                            +Thread.currentThread().getId()
                            +":"+Thread.currentThread().isInterrupted());
                    System.out.println("被中断的线程_"+getId()
                            +":"+isInterrupted());
                    //do my work
                }
                //清理工作，准备结束线程
            }
        }

        public void cancel() {
            //on = false;
            interrupt();
            System.out.println("本方法所在线程实例："+getId());
            System.out.println("执行本方法的线程："+Thread.currentThread().getId());
            //Thread.currentThread().interrupt();//main方法调用cancel方法，所以该语句会终端主线程
        }
    }

    /*try/catch块中包含while循环*/
    private static class TryWhileWhenBlock  extends Thread {
        private volatile boolean on = true;
        private long i =0;

        @Override
        public void run() {
            try {
                while (on) {
                    System.out.println(i++);
                    //抛出中断异常的阻塞方法，抛出异常后，中断标志位改成false
                    synchronized (o){
                        o.wait();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("当前执行线程的中断标志位："
                        +Thread.currentThread().getId()
                        +":"+Thread.currentThread().isInterrupted());
            }finally {
                //清理工作结束线程
            }
        }
        public void cancel() {
            on = false;
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WhileTryWhenBlock whileTryWhenBlock = new WhileTryWhenBlock();
        whileTryWhenBlock.start();
        Thread.sleep(100);
        whileTryWhenBlock.cancel();
//        TryWhileWhenBlock tryWhileWhenBlock = new TryWhileWhenBlock();
//        tryWhileWhenBlock.start();
//        Thread.sleep(100);
//        tryWhileWhenBlock.cancel();
    }
}
