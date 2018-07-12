package com.dang.note.other.lanqiaobei;

public class Caizimu {
    static int in[] = new int[2014];
    static int p = 0;
    static int j = 1;
    static int out = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 106; i++) {
            for (int j = 1; j <= 19; j++) {
                in[p] = j;
                p++;
            }

        }
        System.out.print(p + "");
        while (out == 2011) {
            System.out.println(out + "out");
            out = 0;
            j = 1;
            for (int s = 0; s < 2014; s++) {
                if (in[s] == -1) {
                    out++;
                } else {
                    if (j % 2 == 1) {
                        System.out.println(in[s] + "toout");
                        in[s] = -1;
                        j++;

                    }
                }
            }
        }
        for (int i = 0; i < 2014; i++) {
            if (in[i] != -1) {
                System.out.println("oo" + in[i]);
            }

        }
        System.out.print("dd");
    }

}
