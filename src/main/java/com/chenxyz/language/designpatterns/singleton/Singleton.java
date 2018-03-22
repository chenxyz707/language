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

    private static Singleton singleton;

    public static Singleton getInstance() {
        if(singleton!=null) {
            synchronized (Singleton.class) {
                if(singleton!=null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
