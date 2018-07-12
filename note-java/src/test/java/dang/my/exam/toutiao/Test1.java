package com.dang.my.exam.toutiao;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int d[] = new int[n];
            for (int a = 0; a < n; a++) {
                d[a] = scan.nextInt();
            }
            Arrays.sort(d);
            int ans = 0;
            for (int b = 0; b < d.length - 2; b++) {
                if (d[b + 1] - d[b] <= 10 && d[b + 2] - d[b + 1] <= 10) {
                    d[b] = 0;
                    d[b + 1] = 0;
                    d[b + 2] = 0;
                    b = b + 2;
                }
            }
            Arrays.sort(d);
            for (int a = 0; a < d.length; a++) {

            }
            int add = 3 - (n + ans) % 3;
            System.out.println(ans + add);
        }

    }
}
