package com.dang.my.exam.i360;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String col = scan.nextLine();
            String see1 = scan.nextLine();
            String see2 = scan.nextLine();
            String col2 = reversal(col);
            int f1 = col.indexOf(see1);
            int f2 = col.indexOf(see2, f1 + 1);
            int f11 = col2.indexOf(see1);
            int f22 = col2.indexOf(see2, f11 + 1);
            //System.out.println(f1+":"+f2+"=="+f11+":"+f22);
            boolean forward = false, backward = false;
            if (f1 != -1 && f2 != -1) {
                forward = true;
            }
            if (f11 != -1 && f22 != -1) {
                backward = true;
            }
            if (forward && backward) {
                System.out.println("both");
            } else if (forward) {
                System.out.println("forward");
            } else if (backward) {
                System.out.println("backward");
            } else {
                System.out.println("invalid");
            }
        }
    }

    public static String reversal(String str) {
        String result = "";
        for (int i = str.length() - 1; i > -1; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}	
