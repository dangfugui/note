package com.dang.my.exam.test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        n = scan.nextInt();
        long s[] = new long[(int) (n + 5)];
        for (int a = 0; a < n; a++) {
            s[a] = scan.nextLong();
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = i;
            long num = 0;
            do {
                p = (p + 1) % n;
                if (s[p] > s[i]) {
                    break;
                } else {

                    if (p == i + 1 || p == i - 1 || p == i) {
                        continue;
                    }
                    if (i == 0 && p == n - 1) {
                        continue;
                    }

                    num++;
                }
            } while (p != i);
            ans = ans + num;
            System.out.println(i + "=" + ans);

        }

    }
}
