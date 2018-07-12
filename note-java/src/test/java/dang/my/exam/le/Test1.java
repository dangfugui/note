package com.dang.my.exam.le;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String n = scan.nextLine();
            char arr[] = n.toCharArray();
            Arrays.sort(arr);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != '0') {
                    char s = arr[0];
                    arr[0] = arr[i];
                    arr[i] = s;
                    break;
                }
            }
            String out = new String(arr);
            String b = scan.nextLine();
            if (out.equals(b)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

    }
}
