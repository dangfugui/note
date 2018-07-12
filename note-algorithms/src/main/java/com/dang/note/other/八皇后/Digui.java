package com.dang.note.other.八皇后;

public class Digui {
    public int N = 8;
    public int list[] = new int[16];
    public int anscount = 0;//看有多少种排列方式d

    public static void main(String[] args) {
        Digui digui = new Digui();
        digui.find(1);//从第一个开始
        System.out.println(digui.anscount);
    }

    public void find(int i) {
        if (i > N) {
            print(list);
            anscount++;
            return;
        }
        for (int a = 1; a <= N; a++) {
            list[i] = a;//第i行在第a个位置，a是1-8，每个都遍历一遍
            if (check(i)) {//检查第i行是否符合条件
                find(i + 1);//如果符合条件就找下一行的位置。
            }

        }

    }

    private boolean check(int n) {//检查第n行是否符合条件
        for (int i = 1; i < n; i++) {//从n行之前的检查，
            if (list[n] == list[i] || Math.abs(n - i) == Math.abs(list[n] - list[i])) {
                //第一个条件表示：在同一列；
                //第二个条件表示：是否是同一斜对角线
                //如果两条件都不满足，则符合条件，可以继续下去。
                return false;
            }
        }
        return true;
    }

    private void print(int a[]) {
        for (int b = 0; b < a.length; b++) {
            System.out.print(a[b]);
        }
        System.out.println();
    }

}
