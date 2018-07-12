package com.dang.note.other.lanqiaobei;

import java.util.Scanner;

public class LouCeng {
    public static void main(String[] args) {
        int w, m, n;
        int x1, x2, y1, y2;
        //���Ͻ�Ϊ����ԭ��   ��0,1��
        Scanner scan = new Scanner(System.in);
        w = scan.nextInt();
        m = scan.nextInt();
        n = scan.nextInt();
        y1 = (m - 1) / w;
        y2 = (n - 1) / w;
        if (y1 % 2 == 0) {
            x1 = m - y1 * w;
        } else {
            x1 = (y1 + 1) * w - m + 1;
        }
        if (y2 % 2 == 0) {
            x2 = n - y2 * w;
        } else {
            x2 = (y2 + 1) * w - n + 1;
        }
        System.out.println("x1" + x1 + "y1" + y1 + "x2" + x2 + "y2" + y2);
        int ans = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        System.out.println("ans=" + ans);
    }

}
