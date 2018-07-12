package com.dang.note.sort;

/**
 * Created by duang on 2017/3/13.
 */

/***
 * 选择排序 寻找最小的和index++交换   O(n^2)   交换不稳定
 */
public class 选择排序 {
    public static void main(String[] args) {
        Integer array[] = {1, 5, 9, 3, 5, 7, 4, 5, 6, 8, 5, 2};
        SortSuper<Integer> sort = new SelectionSort<Integer>();
        sort.sort(array);
        for (Integer i : array) {
            System.out.print(i + " ");
        }
    }
}

class SelectionSort<T extends Comparable> extends SortSuper<T> {
    @Override
    public T[] sort(T[] arrays) {
        int index = 0;
        while (index < arrays.length) {
            int minIndex = index;
            for (int i = index; i < arrays.length; i++) {
                if (arrays[minIndex].compareTo(arrays[i]) > 0) {
                    minIndex = i;
                }
            }
            T swop = arrays[index];
            arrays[index] = arrays[minIndex];
            arrays[minIndex] = swop;
            index++;
        }
        return arrays;
    }
}
