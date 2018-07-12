package com.dang.my.exam.sohu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test2 {
    public static boolean find = true;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.nextLine();
            str = str + str;
            char[] arr = str.toCharArray();
            int ans = 9999;
            for (int b = 0; b < arr.length; b++) {
                Set set = new HashSet();
                set.clear();
                int last = Integer.MAX_VALUE;
                int l;
                for (l = b; l < arr.length; l++) {
                    if (arr[l] >= 'A' && arr[l] <= 'E') {
                        set.add(arr[l]);
                        if (set.size() == 5) {
                            set.clear();
                            last = l;
                            break;
                        }
                    }
                }
                int as = last - b;

                if (as < ans) {
                    ans = as;
                }
            }
            System.out.println(str.length() / 2 - ans - 1);
        }
    }

}	
