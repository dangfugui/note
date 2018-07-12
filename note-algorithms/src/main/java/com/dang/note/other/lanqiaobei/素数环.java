package com.dang.note.other.lanqiaobei;

/**
 * 素数环问题： 把从1到20这20个数摆成一个环，要求相邻两个数的和是一个素数。u
 *
 * @author duang
 */
public class 素数环 {
    public static void main(String[] args) {
        素数环 s = new 素数环();
        s.start();
        System.out.println(s.ans);
    }

    int N = 20;
    int ans = 0;
    int list[] = new int[N + 2];

    private void start() {
        // TODO 自动生成的方法存根
        list[1] = 1;
        find(2);
        //因为是环形的  所以以1的位置为根
        /*for(int a=1;a<=N;a++){
			list[1]=a;
			find(2);
		}*/
    }

    public void find(int i) {
        if (i > N) {
            //print();
            ans++;
            return;
        }
        for (int a = 1; a <= N; a++) {
            list[i] = a;
            if (check(i)) {
                find(i + 1);
                list[i] = 0;
            }
        }
    }

    private void print() {
        // TODO 自动生成的方法存根
        for (int a = 0; a < list.length; a++) {
            System.out.print(list[a] + " ");
        }
        System.out.println();
    }

    private boolean check(int i) {
        if (i == N && (!susu(list[i] + list[1]))) {
            return false;
        }
        if (!susu(list[i - 1] + list[i])) {
            return false;
        }
        for (int a = 1; a < i; a++) {
            if (list[a] == list[i]) {
                return false;
            }
        }
        return true;
    }

    //判断i 是否为素数
    private boolean susu(int i) {
        int n = (int) Math.sqrt(i) + 1;
        for (int a = 2; a <= n; a++) {
            if (i % a == 0) {
                return false;
            }
        }
        return true;

    }
}
