package com.dang.note.other.lanqiaobei;

import java.math.BigDecimal;

/*
 *    构造一个尽可能大的数，使其从高到低前一位能被1整除，前2位能被2整除，……，前n位能被n整除。u
*/
public class 构造高精度数据 {

    public static void main(String[] args) {
        new 构造高精度数据().start();
        BigDecimal bg = new BigDecimal("123.123");

    }

    private int A[] = new int[200];
    private int B[] = new int[200];
    int i, j, k, n, r;

    public void start() {
        A[1] = 1;
        for (i = 2; i <= 100; i++) {
            A[i] = 0;  //置初值:首位为1,其余为0
        }
        n = 1;
        i = 1;
        while (A[1] <= 9) {
            if (i >= n)    //发现有更大的满足条件的高精度数据
            {
                n = i;               //转存到数组B中
                for (k = 1; k <= n; k++) {
                    B[k] = A[k];
                }
            }
            i = i + 1;
            r = 0;
            for (j = 1; j <= i; j++)     //检查第i位是否满足条件
            {
                r = r * 10 + A[j];
            }
            r = r % i;
            if (r != 0)               //若不满足条件
            {
                A[i] = A[i] + i - r;      //第i位可能的解
                while (A[i] > 9 && i > 1)  //搜索完第i位解,回溯到前一位
                {
                    A[i] = 0;
                    i = i - 1;
                    A[i] = A[i] + i;

                }
            }
        }
        for (int a = 0; a < 100; a++) {
            System.out.print(B[a]);
        }
    }

    private void copy() {
        // TODO 自动生成的方法存根
        for (int a = 0; a < B.length; a++) {
            A[a] = B[a];
        }
    }
}
