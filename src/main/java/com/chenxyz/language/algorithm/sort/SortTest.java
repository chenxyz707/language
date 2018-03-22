package com.chenxyz.language.algorithm.sort;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-21
 */
public class SortTest {

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Quick Sort Test");
        int[] array = {23, 4, 65, 98, 65, 22, 3, 9};
        printArray(array);
        QuickSort quickSort = new QuickSort();
        quickSort.sort2(array, 0, array.length-1);
        printArray(array);

        System.out.println("Insert Sort Test");
        array = new int[]{23, 4, 65, 98, 65, 22, 3, 9};
        printArray(array);
        InsertSort insertSort = new InsertSort();
        insertSort.sort(array);
        printArray(array);


    }
}
