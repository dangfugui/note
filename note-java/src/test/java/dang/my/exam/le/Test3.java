package com.dang.my.exam.le;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = scan.next();
                map[i] = line.toCharArray();
            }
            int ans = 0;
            if (is(map)) {
                ans++;
            }
            System.out.println(ans);
        }
    }

    private static boolean is(char[][] map) {
        for (int a = 0; a < map.length; a++) {
            for (int b = 0; b < map[0].length; b++) {
                if (map[a][b] == '1') {
                    int c = 0;
                    for (c = 0; c <= map[0].length; c++) {
                        if (map[a][c] != '1') {
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }
}	
