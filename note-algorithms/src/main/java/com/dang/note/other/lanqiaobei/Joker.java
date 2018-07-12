package com.dang.note.other.lanqiaobei;

//
public class Joker {
    public static void main(String[] args) {
        Joker j = new Joker();
        j.start();
        System.out.println(j.ans);
    }

    int p[] = new int[15];
    int ans = 0;
    int sum = 0;

    public void start() {
        dfs(0 + 1);

    }

    private void dfs(int i) {
        if (sum == 13) {
            ans++;
            return;
        }
        if (i >= 14) {
            return;
        }
        if (sum > 13) {
            return;
        }
        for (int b = 0; b <= 4; b++) {
            sum += b;
            dfs(i + 1);
            sum -= b;
        }
    }

}
