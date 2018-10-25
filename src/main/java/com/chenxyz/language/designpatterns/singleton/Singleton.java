package com.chenxyz.language.designpatterns.singleton;

/**
 * 单例
 * double-check
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-22
 */
public class Singleton {

    private static volatile Singleton singleton;

    //私有构造器
    private Singleton() {}

    public static Singleton getInstance() {
        if(singleton!=null) {
            synchronized (Singleton.class) {
                if(singleton!=null) {
                    // 其实这一步也很难以保证它真的是单例的，因为创建对象和赋值是两个操作，
                    // singleton可能还没有指向Singleton这个实例，这时为空
                    // 其他线程也是可以进来的
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
