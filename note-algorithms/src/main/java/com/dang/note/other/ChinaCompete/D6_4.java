package com.dang.note.other.ChinaCompete;

public class D6_4 {
    public char map[][] = {
            {'*', '*', '*', '*', '*', '*', '*'},
            {'*', 'A', '+', '-', '+', '-', '*'},
            {'*', '-', '+', '-', '-', '+', '*'},
            {'*', '-', '+', '+', '+', '-', '*'},
            {'*', '+', '-', '+', '-', '+', '*'},
            {'*', 'B', '+', '-', '+', '-', '*'},
            {'*', '*', '*', '*', '*', '*', '*'}
    };
    public char map2[][] = {
            {'*', '*', '*', '*', '*', '*', '*'},
            {'*', '0', '0', '0', '0', '0', '*'},
            {'*', '0', '0', '0', '0', '0', '*'},
            {'*', '0', '0', '0', '0', '0', '*'},
            {'*', '0', '0', '0', '0', '0', '*'},
            {'*', '0', '0', '0', '0', '0', '*'},
            {'*', '*', '*', '*', '*', '*', '*'}
    };
    public int movex[] = {0, 0, -1, 1};
    public int movey[] = {-1, 1, 0, 0};
    public int ans = 9999;
    public char c = '?';
    public int ax, ay, bx, by;
    public int sang = 0;

    public static void main(String[] args) {
        D6_4 a = new D6_4();
        a.run();
    }

    public void run() {
        for (int a = 0; a < map.length; a++) {
            for (int b = 0; b < map[0].length; b++) {
                if (map[a][b] == 'A') {
                    ax = a;
                    ay = b;
                }
                if (map[a][b] == 'B') {
                    bx = a;
                    by = b;
                }
            }
        }
        System.out.println("" + ax + ay + ">>" + bx + by);
        find(ax, ay, 0);
        System.out.println(ans);
    }

    private void find(int ax2, int ay2, int bu) {
        if (map[ax2][ay2] == '*') {
            return;
        }
        for (int i = 0; i < movex.length; i++) {
            int x = ax2 + movex[i];
            int y = ay2 + movey[i];
            if (map2[x][y] == '1') {
                continue;
            }
            if (map[x][y] == '*') {
                continue;
            }
            System.out.println("move" + x + y);
            if (x == bx && y == by) {
                if (ans > bu) {
                    ans = bu;
                }
                return;
            } else if (c == '?') {
                c = map[x][y];

                find(x, y, bu++);
            } else if (c == '-') {
                if (map[x][y] == '+') {
                    c = '+';
                    find(x, y, bu++);
                }
            } else if (c == '+') {
                if (map[x][y] == '-') {
                    c = '-';

                    find(x, y, bu++);
                }
            }

        }
        map2[ax2][ay2] = '1';
    }

}
