package com.dang.note.other.ChinaCompete;

public class D6_1 {
    static int ans = 0;

    public static void main(String[] args) {
        for (int a = 9; a >= 1; a--) {
            for (int b = a - 1; b >= 0; b--) {
                for (int c = b - 1; c >= 0; c--) {
                    //System.out.println(""+a+b+c);
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

}
