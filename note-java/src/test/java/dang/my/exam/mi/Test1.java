package com.dang.my.exam.mi;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] word = line.split(" ");
            for (int i = word.length - 1; i > 0; i--) {
                System.out.print(word[i] + " ");
            }
            System.out.println(word[0]);
        }
    }

}
