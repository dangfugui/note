package com.dang.my.exam.toutiao;

import java.util.Scanner;

public class Test2 {
    public static boolean find = true;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
            }
            int ans = 0;
            for (int x = 0; x < n - 1; x++) {
                for (int y = x + 1; y < n; y++) {
                    if ((a[x] ^ a[y]) > m) {
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }

}	
