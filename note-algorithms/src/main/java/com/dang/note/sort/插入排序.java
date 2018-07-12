package com.dang.note.sort;

/**
 * Created by duang on 2017/3/13.
 */

/***
 * 插入排序 查看Array[index++] 需要插入到Array[0:index-1]中的那个位置   O(n^2)  稳定
 */
public class 插入排序 {
    public static void main(String[] args) {
        Integer array[] = {10, 5, 9, 3, 5, 7, 4, 5, 6, 8, 5, 2};
        SortSuper<Integer> sort = new InsertSort<Integer>();
        sort.sort(array);
        for (Integer i : array) {
            System.out.print(i + " ");
        }
    }
}

//10,5,9,3,5,7,4,5,6,8,5,2
class InsertSort<T extends Comparable> extends SortSuper<T> {
    @Override
    public T[] sort(T[] arrays) {
        for (int index = 1; index < arrays.length; index++) {
            print(arrays);
            for (int i = 0; i <= index; i++) {
                if (arrays[i].compareTo(arrays[index]) > 0) {
                    T p = arrays[index];
                    for (int a = index; a > i; a--) {
                        arrays[a] = arrays[a - 1];
                    }
                    arrays[i] = p;
                    break;
                }
            }
        }
        return arrays;
    }
}