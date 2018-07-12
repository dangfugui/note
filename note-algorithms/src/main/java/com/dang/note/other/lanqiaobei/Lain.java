package com.dang.note.other.lanqiaobei;

public class Lain {
    public static void main(String[] args) {
        char str[] = new char[2014];
        int p = 0;
        for (int i = 0; i < 106; i++) {
            for (int j = 0; j < 19; j++) {
                str[p] = (char) ('a' + j);
                p++;
            }
        }
        /*for (int i = 0; i < str.length; i++) {
			System.out.print(str[i]);
		}*/
        for (int a = 0; a < 11; a++) {
            int out = 0;
            int pp = 1;
            for (int i = 0; i < str.length; i++) {

                if (str[i] == '0') {
                    out++;
                } else {
                    if (pp % 2 == 1) {
                        str[i] = '0';
                    }
                    pp++;
                }
            }
            System.out.print(out + "out");
            if (out == 2011) {
                break;
            }
        }

        for (int i = 0; i < str.length; i++) {
            if (str[i] != '0') {
                System.out.print(str[i]);
            }
        }
    }
}
