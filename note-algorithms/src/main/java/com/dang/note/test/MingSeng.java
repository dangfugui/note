package com.dang.note.test;

public class MingSeng {
    public static void main(String[] args) {
        System.out.println("dd");
        for (int a = -99; a < 100; a++) {
            for (int b = -99; b < 100; b++) {
                for (int c = -99; c < 100; c++) {
                    for (int d = -99; d < 100; d++) {
                        if ((a - b == 9) && (c - d == 14)) {
                            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);

                        }
                        //System.out.println("a"+a+"b"+b+"c"+c+"d"+d);

                    }

                }

            }

        }
    }
}
