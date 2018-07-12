package com.dang.note.sort;

import java.util.Arrays;

/**
 * Created by duang on 2017/3/13.
 */

/***
 * 归并排序 分治思想   合并是重点   O(n*log（n）)  稳定
 */
public class 归并排序 {
    public static void main(String[] args) {
        Integer array[] = {10, 5, 9, 3, 5, 7, 4, 5, 6, 8, 5, 2};
        SortSuper<Integer> sort = new MergeSort<Integer>();
        sort.sort(array);
        for (Integer i : array) {
            System.out.print(i + " ");
        }
    }
}

//10,5,9,3,5,7,4,5,6,8,5,2
class MergeSort<T extends Comparable> extends SortSuper<T> {
    @Override
    public T[] sort(T[] arrays) {
        sort(arrays, 0, arrays.length - 1);
        return arrays;
    }

    public void sort(T[] array, int start, int end) {
        if (start < end) {
            int half = (start + end) / 2;
            sort(array, start, half);
            sort(array, half + 1, end);
            merge(array, start, end);
        }
    }

    private void merge(T[] array, int start, int end) {
        int half = (start + end) / 2;
        T[] part1 = Arrays.copyOfRange(array, start, half + 1);
        T[] part2 = Arrays.copyOfRange(array, half + 1, end + 1);
        int p1 = 0, p2 = 0;
        for (int i = start; i <= end; i++) {
            if (p1 < part1.length && p2 < part2.length) {
                if (part1[p1].compareTo(part2[p2]) < 0) {
                    array[i] = part1[p1++];
                } else {
                    array[i] = part2[p2++];
                }
            } else if (p1 < part1.length) {
                array[i] = part1[p1++];
            } else if (p2 < part2.length) {
                array[i] = part2[p2++];
            }
        }
    }
}