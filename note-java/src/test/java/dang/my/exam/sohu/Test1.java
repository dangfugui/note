package com.dang.my.exam.sohu;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.nextLine();
            int l = scan.nextInt();
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            char[] out = str.toCharArray();

            for (int a = 0; a < l; a++) {
                int ind;
                for (ind = out.length - 1; ind >= 0; ind--) {
                    if (out[ind] == arr[a]) {
                        break;
                    }
                }
                if (ind >= 0) {
                    out[ind] = 'a';
                }
            }
            String pr = "";
            for (char c : out) {
                if (c != 'a') {
                    pr += c;
                }
            }
            System.out.println(pr);
        }

    }
}
