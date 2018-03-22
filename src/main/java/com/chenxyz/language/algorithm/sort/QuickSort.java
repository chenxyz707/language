package com.chenxyz.language.algorithm.sort;

/**
 * 快速排序
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class QuickSort {

    public void sort(int[] array, int low, int high) {
        if (low < high) {
            int middle = partation(array, low, high);
            sort(array, low, middle-1);
            sort(array, middle+1, high);
        }
    }

    public int partation(int[] array, int low, int high) {
        int tmp = array[low];// 数组的第一个元素作为中轴
        while(low < high) {
            //使用等号是为了防止重复数据导致low或index不移动，陷入死循环
            while(low < high && array[high] >= tmp) {
                high--;
            }
            array[low] = array[high];
            while(low < high && array[low] <= tmp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = tmp;
        return low;
    }

    public void sort2(int[] array, int low, int high) {
        if (low < high) {
            int middle = partation2(array, low, high);
            sort(array, low, middle-1);
            sort(array, middle+1, high);
        }
    }

    /**
     * 快速排序的优化
     * 三数取中（median-of-three）
     * @param array
     * @param low
     * @param high
     * @return
     */
    public int partation2(int[] array, int low, int high) {
        int mid = (low+high)/2;
        //三数取中
        //下面的两步保证了array[hi]是最大的；
        if (array[mid] > array[high]) {
            swap(array, mid, high);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
        //接下来只用比较array[lo]和array[mid]，让较大的在array[lo]位置就OK。
        if (array[mid] > array[low]) {
            swap(array, mid, low);
        }
        int tmp = array[low];// 数组的第一个元素作为中轴
        while(low < high) {
            //使用等号是为了防止重复数据导致low或index不移动，陷入死循环
            while(low < high && array[high] >= tmp) {
                high--;
            }
            array[low] = array[high];
            while(low < high && array[low] <= tmp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = tmp;
        return low;
    }

    public void swap(int[] array,int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
