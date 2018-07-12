package com.dang.my.exam.mi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test3 {
    private static Scanner scan;

    public static void main(String[] args) {

        scan = new Scanner(System.in);
        String nn = scan.nextLine();
        int n = Integer.parseInt(nn.trim());
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            String line = scan.nextLine();
            //System.out.println(i+"line:"+line);
            list.add(line);
        }
        for (String l : list) {
            work(l);
        }

    }

    private static void work(String line) {
        String strs[] = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
        for (int a = 0; a < strs.length; a++) {
            int num = a - 8;
            if (num < 0) {
                num = 10 + num;
            }
            line = replace(line, strs[a], num);
        }

        char ch[] = line.toCharArray();
        Arrays.sort(ch);
        String out = "";
        for (int b = 0; b < ch.length; b++) {
            out += ch[b];
        }
        System.out.println(out);
    }

    private static String replace(String line, String str, int num) {
        List<Integer> list = new ArrayList<Integer>();
        String return_line = new String(line);
        char c[] = str.toCharArray();
        for (int a = 0; a < c.length; a++) {
            int find = line.indexOf(c[a]);

            if (find != -1) {
                line = line.substring(0, find) + line.substring(find + 1);
                list.add(find);
            } else {
                return return_line;
            }
        }

        return line + num;

    }
}	
