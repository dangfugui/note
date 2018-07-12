package com.dang.note.other.oj;

import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        long a = 1, b = 1, ans = 1, n;
        Scanner cin = new Scanner(System.in);
        n = cin.nextInt();

        for (long i = 3; i <= n; i++) {
            ans = (a + b) % 10007;
            a = b % 10007;
            b = ans;
        }
        System.out.println(ans);
    }
}
