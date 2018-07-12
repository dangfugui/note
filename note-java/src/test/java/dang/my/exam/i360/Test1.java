package com.dang.my.exam.i360;

import java.util.Scanner;

public class Test1 {
    static int data[];
    static int data_b[];
    static int data_l[];
    static int max, handle = 1;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        max = scan.nextInt();
        data = new int[max + 1];
        data[1] = 1;
        data_b = new int[max + 1];
        data_l = new int[max + 1];
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            String line = scan.nextLine();
            if (line.contains("new")) {
                int size = Integer.parseInt(line.substring(4).trim());
                //System.out.println("size"+size);
                mnew(size);
            } else if (line.contains("del")) {
                int h = Integer.parseInt(line.substring(4).trim());
                del(h);
            } else if (line.contains("def")) {
                def();
            }
        }

    }

    private static void def() {
        int last = max;
        int begin = 1;
        while (begin < last) {
            for (int i = begin; i < last; i++) {
                if (data[i] == 0) {
                    begin = i;
                    break;
                }
            }
            for (int i = last; i > begin; i--) {
                if (data[i] != 0) {
                    last = i;
                    break;
                }
            }
            data[begin] = data[last];
            data[last] = 0;
        }

    }

    private static void del(int h) {
        if (h > handle) {
            System.out.println("ILLEGAL_OPERATION");
            return;
        }

        for (int i = data_b[h]; i < data_l[h]; i++) {
            data[i] = 0;
        }

    }

    public static void mnew(int size) {
        int maxsize = 0;
        for (int p = 1; p <= max; p++) {
            if (data[p] == 0) {
                maxsize++;
                if (maxsize >= size) {
                    data_b[handle] = p - size;
                    data_l[handle] = p - 1;
                    System.out.println(handle);
                    handle++;
                    return;
                }
            } else {
                maxsize = 0;
            }
        }
        System.out.println("NULL");
    }
}
