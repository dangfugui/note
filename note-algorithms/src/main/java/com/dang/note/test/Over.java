package com.dang.note.test;

public class Over {
    public static void main(String[] args) {
        i:
        for (int i = 0; i < 10; i++) {
            a:
            for (int a = 0; a < 10; a++) {
                b:
                for (int b = 0; b < 10; b++) {
                    System.out.println(i + "-" + a + "-" + b);
                    break b;
                }

            }

        }
    }
}
