package com.chenxyz.language.thread.base.threadlocal;

/**
 * ThreadLocal每个线程中的数据都是独立的
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-17
 */
public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Hello World";
        }
    };

    public void test(){
        Thread[] runs = new Thread[3];
        for(int i =0;i<runs.length;i++){
            runs[i]=new Thread(new TestRunnable(i));
        }
        for(int i =0;i<runs.length;i++){
            runs[i].start();
        }
        threadLocal.remove();
    }

    private static class TestRunnable implements Runnable{

        private int id;

        public TestRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id+" start");
            String s = threadLocal.get();
            s = s+"_"+id;
            threadLocal.set(s);
            System.out.println("value of id ["+id+"] is : "+s);
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        test.test();
    }
}
