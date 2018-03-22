package com.chenxyz.language.algorithm.sort;

/**
 * 插入排序
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-21
 */
public class InsertSort {

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i;
            while (j > 0 && key < array[j-1]) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = key;
        }
    }
}
