package com.dang.note.sort;

/**
 * Created by duang on 2017/3/13.
 * 先将整个待排序序列分割成若干个子序列(由相隔某个“增量”的元素组成)，分别进行
 * 插入排序，然后依次缩减增量再进行排序，当增量为 1 时，对整个序列来一次直接插入排
 * 序，即可完成排序。
 * O(N^1.3)  不稳定。因为按照增量来排序，很可能因为交换而改变两个相等的值的相对位置。
 */
public class 希尔排序 {
    public static void main(String[] args) {
        Integer array[] = {4, 3, 6, 1};
        SortSuper<Integer> sort = new ShellSort<Integer>();
        sort.sort(array);
        for (Integer i : array) {
            System.out.print(i + " ");
        }
    }
}

class ShellSort<T extends Comparable> extends SortSuper<T> {

    /**
     * 希尔排序
     */
    public T[] sort(T[] arrays) {
        int len = arrays.length;
        for (int i = len >> 1; i > 0; i >>= 1) { //每次都缩小一半 这里使用位操作符
            /**
             * 插入排序的过程
             */
            print(arrays);
            for (int j = i; j < arrays.length; j++) {
                T temp = arrays[j];
                int k = j;
                for (; k >= i && arrays[k - i].compareTo(temp) > 0; k -= i) {
                    arrays[k] = arrays[k - i];
                }
                arrays[k] = temp;
            }
        }
        return arrays;
    }
}
