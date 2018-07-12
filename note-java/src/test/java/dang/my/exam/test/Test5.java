package com.dang.my.exam.test;

import java.util.Scanner;

public class Test5 {
    static int ans = 0;
    static int sum = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        sum = scan.nextInt();
        int arr[] = new int[n];
        for (int a = 0; a < n; a++) {
            arr[a] = scan.nextInt();
        }

        find(arr, 0, 0, 0);

        System.out.println(ans);
    }

    private static void find(int[] arr, int b, int l, int tol) {
        //System.out.println(b+"  "+l+"  "+tol);
        if (l >= arr.length) {
            if (tol == sum) {
                ans++;
            }
            return;
        }
        if (tol > sum) {
            return;
        } else if (tol < sum) {
            find(arr, b, l + 1, tol);
            find(arr, b, l + 1, tol + arr[l]);
        } else {
            ans++;
            return;
        }
    }
}	
/*

5 15
5 5 10 2 3
*/
