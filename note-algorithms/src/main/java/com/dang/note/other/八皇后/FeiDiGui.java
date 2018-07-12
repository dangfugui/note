package com.dang.note.other.八皇后;

public class FeiDiGui {
    int N = 8;
    int list[] = new int[10];

    public static void main(String[] args) {
        FeiDiGui f = new FeiDiGui();
        f.backDate(8);
        f.print(f.list);

    }

    public void backDate(int N) {
        int i = 1;
        int n = 1;
        while (n > 0) {
            list[n] = i;
            if (check(n)) {
                n++;
            }
        }

    }

    private boolean check(int n) {
        for (int i = 1; i < n; i++) {
            if (list[n] == list[i] || Math.abs(n - i) == Math.abs(list[n] - list[i])) {
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
