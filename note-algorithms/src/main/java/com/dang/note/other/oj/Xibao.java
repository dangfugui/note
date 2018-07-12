package com.dang.note.other.oj;

import java.util.Scanner;

public class Xibao {
    public int[][] map = new int[99][99];
    public int m, n, ans = 0;
    public int[] dx = {-1, 0, 1, 0};
    public int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        new Xibao().start();
    }

    public void start() {
        init();
        for (int a = 1; a <= m; a++) {
            for (int b = 1; b <= n; b++) {
                if (map[a][b] != 0) {
                    dfs(a, b);
                    ans++;
                }

            }
        }
        System.out.println(ans);
    }

    private void dfs(int a, int b) {
        // TODO �Զ����ɵķ������
        map[a][b] = 0;
        for (int i = 0; i < 4; i++) {
            int y = a + dy[i];
            int x = b + dx[i];
            if (map[y][x] != 0) {
                dfs(y, x);
            }
        }

    }

    private void init() {
        // TODO �Զ����ɵķ������
        Scanner scan = new Scanner(System.in);
        m = scan.nextInt();
        n = scan.nextInt();
        //System.out.println(m+"-"+n);

        for (int a = 1; a <= m; a++) {
            for (int b = 1; b <= n; b++) {
                map[a][b] = scan.nextInt();
            }
        }
    }
}


/*
 2��ϸ�� һ��������������0��9���,����1��9����ϸ��,ϸ��
�Ķ���Ϊ��ϸ�������������һ���ϸ��������Ϊͬһϸ��,
������������е�ϸ��������
���룺����m,n(m�У�n��)
      ����
�����ϸ���ĸ�����

4 10
0 2 3 4 5 0 0 0 6 7
1 0 3 4 5 6 0 5 0 0
2 0 4 5 6 0 0 6 7 1
0 0 0 0 0 0 0 0 8 9

**/