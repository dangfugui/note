package com.dang.my.exam.le;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;

public class Test4 {
    public static int end, result = Integer.MAX_VALUE;
    public static Stack<Integer> ansStack = new Stack<Integer>();
    public static Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();

    public static void main(String[] args) {
        int ans = Integer.MAX_VALUE;
        Scanner scan = new Scanner(System.in);
        int start = scan.nextInt();
        end = scan.nextInt();
        map = new HashMap<Integer, Map<Integer, Integer>>();
        while (scan.hasNext()) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            Map m = new HashMap<Integer, Integer>();
            m.put(b, c);
            map.put(a, m);
        }
        Stack<Integer> stack = new Stack<Integer>();
        find(start, stack, Integer.MAX_VALUE);
        while (!ansStack.isEmpty()) {
            System.out.print(ansStack.peek() + "->");
        }
        System.out.println(result);
    }

    private static void find(int start, Stack stack, int ans) {
        Map<Integer, Integer> ma = map.get(start);
        for (Entry<Integer, Integer> en : ma.entrySet()) {
            stack.push(en.getKey());
            if (en.getKey() == end) {
                if (ans + en.getValue() < result) {
                    ansStack = stack;
                    result = ans + en.getValue();
                }
            } else {
                find(en.getKey(), stack, ans + en.getValue());
            }
        }
    }

}	
