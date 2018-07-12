package com.dang.my.exam.le;

import java.util.Scanner;

public class Test2 {
    public static boolean find = true;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            getans(n, 0, 0);
            System.out.println(ans);
            find = true;
            ans = Integer.MAX_VALUE;
        }
    }

    public static void getans(int n, int h, int now) {
        if (h > ans || h > 20) {
            return;
        }

        if (((now + h) == n) || ((now - h) == n)) {
            ans = h;
            find = false;
        } else {
            getans(n, h + 1, now + h);
            getans(n, h + 1, now - h);

        }

        return;
    }
}	
