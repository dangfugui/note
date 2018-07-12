package com.dang.java.my.util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by duang on 2017/3/13.
 */
public class Queae1 {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<String>();
        queue.add("1");
        queue.add("2");
        System.out.println(queue.poll());

    }
}
