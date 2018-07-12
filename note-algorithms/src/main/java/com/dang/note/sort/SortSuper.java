package com.dang.note.sort;

/**
 * Created by duang on 2017/3/13.
 */

public abstract class SortSuper<T extends Comparable> {
    public abstract T[] sort(T[] arrays);

    public void print(T[] arrays) {
        for (T t : arrays) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}