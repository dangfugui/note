package com.dang.my.exam.test;

import java.util.Scanner;
import java.util.Stack;

public class Test6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int a = 0; a < 100; a++) {
            for (int b = 2; b < 20; b++) {
                System.out.println(a + "->" + b + "=" + baseString(a, b));
            }
        }
        //		while(scan.hasNext()){
        //			int m=scan.nextInt();
        //			int n=scan.nextInt();
        //			System.out.println(baseString(m, n));
        //		}

    }

    public static String baseString(int num, int base) {
        if (num == 0) {
            return "0";
        }
        Stack<Integer> s = new Stack<Integer>();
        while (num != 0) {
            Integer in = num % base;
            s.push(in);
            num /= base;
        }
        String ans = "";
        while (!s.isEmpty()) {
            int out = s.pop();

            if (out > 9) {
                char o = (char) (out - 10 + (int) 'A');
                ans += o;
            } else {
                ans += out;
            }

        }
        return ans;
    }
}	
