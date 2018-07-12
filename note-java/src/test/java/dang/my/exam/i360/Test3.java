package com.dang.my.exam.i360;

import java.util.Scanner;
import java.util.Stack;

public class Test3 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        for (int a = 2; a < 5000; a++) {
            //int a=scan.nextInt();
            int sum = 0;
            for (int b = 2; b < a; b++) {
                int wei = baseString(a, b);
                sum += wei;
            }
            System.out.println(sum + "/" + (a - 2));
        }
    }

    public static int baseString(int num, int base) {

        StringBuffer str = new StringBuffer("");
        Stack<Integer> s = new Stack<Integer>();
        while (num != 0) {
            Integer in = num % base;
            s.push(in);
            num /= base;
        }
        int sum = 0;
        while (!s.isEmpty()) {
            sum += s.pop();

        }
        return sum;
    }
}	
