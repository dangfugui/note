package com.dang.my.exam.mi;

import java.util.Scanner;

public class Test2 {
    static int max = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int list[] = new int[n];
        for (int a = 0; a < n - 1; a++) {
            int pa = scan.nextInt();
            int ch = scan.nextInt();
            list[ch] = pa;

        }

        for (int a = 0; a < list.length; a++) {
            find(list, a, 0);
        }
        System.out.println(max);
    }

    private static void find(int[] list, int a, int h) {

        if (a == 0) {
            h++;
            if (h > max) {
                max = h;
            }
            return;
        }

        find(list, list[a], h + 1);
    }
}	
