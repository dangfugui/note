package com.dang.note.sort;

/**
 * Created by duang on 2017/3/13.
 */

/**
 * 原理
 * 分治思想，给定待排序数组 a[0…n-1],以 a[0]为关键字，把数组分为 a[0…k]和 a[k+1…n-
 * 1],使得第一个数组中的数都小于 a[0]，第二个数组中的数都大于 a[0]，重复执行上述过程（递
 * 归），即可完成排序。
 * 快速排序的实现和归并排序十分的类似。都是有两个方法完成，快速排序的两个方法一
 * 个是完成划分过程，将数组按照某个元素划分成两部分，左面的都小于这个元素，右面的都
 * 大于这个元素，一个是完成递归调用的过程。
 */
public class 快速排序 {
    public static void main(String[] args) {
        Integer array[] = {10, 5, 9, 3, 5};
        SortSuper<Integer> sort = new QuickSort<Integer>();
        sort.sort(array);
        for (Integer i : array) {
            System.out.print(i + " ");
        }
    }
}

//10,5,9,3,5,7,4,5,6,8,5,2
class QuickSort<T extends Comparable> extends SortSuper<T> {
    @Override
    public T[] sort(T[] arrays) {
        quickSort(arrays, 0, arrays.length - 1);
        return arrays;
    }

    //递归调用
    public void quickSort(T[] a, int left, int right) {
        if (left < right) {
            print(a);
            int n = partition(a, left, right);
            quickSort(a, left, n - 1);
            quickSort(a, n + 1, right);
        }
    }

    /**
     * 快速排序--辅助方法
     * 将原数组划分成为两个部分 使得左面的都小于某个数 右面的都大于某个数
     */
    public int partition(T[] a, int left, int right) {
        T num = a[left]; //使用第一个元素作为划分依据元素
        while (left < right) {
            //完成划分过程 使得左面的都小于某个数 右面的都大于某个数
            while (a[right].compareTo(num) > 0) {
                right--;
            }
            a[left] = a[right];
            while ((left < right) && a[left].compareTo(num) <= 0) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = num;
        return left;
    }
}