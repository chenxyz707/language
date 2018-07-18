package com.chenxyz.language.thread.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子数组
 * AtomicIntegerArray(int[])构造方法会将数组复制一份
 * 对AtomicIntegerArray进行修改不会影响原数组
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-18
 */
public class AtomicArray {

    static int[] value = new int[]{1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
