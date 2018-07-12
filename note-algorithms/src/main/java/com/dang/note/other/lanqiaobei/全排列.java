package com.dang.note.other.lanqiaobei;

public class 全排列 {

    public static void main(String[] args) {
        全排列 q = new 全排列();
        q.start();
    }

    private int N = 3;
    private int list[] = new int[N];

    public void start() {
        for (int a = 0; a < N; a++) {
            list[a] = a + 1;
        }
        tree(0);

    }

    public void tree(int i) {
        if (i >= N) {
            print();
            return;
        }
        //和自身交换  即不交换这个位置u
        for (int a = i; a < N; a++) {
            swap(i, a);
            tree(i + 1);
            swap(i, a);
        }
    }

    private void print() {
        for (int a = 0; a < list.length; a++) {
            System.out.print(list[a]);
        }
        System.out.println();

    }

    public void swap(int a, int b) {
        int c = list[a];
        list[a] = list[b];
        list[b] = c;
    }
}
