package com.dang.note.test;

import java.util.Scanner;

public class saizi {
    static String str;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        dodo(0, str.length() - 1, 0);
        System.out.println(ans);
    }

    public static void dodo(int start, int end, int h) {
        //System.out.println(""+start+"->"+end+"="+h);
        if (start >= end) {
            if (start == end) {
                h++;
            }
            if (h > ans) {
                ans = h;
            }
            return;
        }
        if (str.charAt(start) == str.charAt(end)) {
            dodo(start + 1, end - 1, h + 2);
        }
        dodo(start + 1, end, h);
        dodo(start, end - 1, h);
    }
}
