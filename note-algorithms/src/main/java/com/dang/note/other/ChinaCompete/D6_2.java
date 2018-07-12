package com.dang.note.other.ChinaCompete;

public class D6_2 {
    private int[] str = {1, 2, 3, 4, 5, 6, 8, 9, 10, 12};
    int sum = 0;

    public static void main(String[] args) {
        D6_2 q = new D6_2();
        q.run();
    }

    public void run() {
        tree(0);
    }

    public void tree(int i) {
        if (i >= str.length) {
            panduan();
            return;
        }
        for (int a = i; a < str.length; a++) {
            int z = str[i];
            str[i] = str[a];
            str[a] = z;//����
            tree(i + 1);
            z = str[i];
            str[i] = str[a];
            str[a] = z;//����
        }
    }

    private void panduan() {
        int a = str[0] + str[1] + str[2] + str[3];
        int b = str[4] + str[1] + str[5] + str[8];
        int c = str[4] + str[2] + str[6] + str[9];
        int d = str[0] + str[5] + str[7] + str[9];
        int e = str[3] + str[6] + str[7] + str[8];
        if (a == b && a == c && a == d && a == e) {
            sum++;
            System.out.println(sum);
        }
    }
}
