package com.dang.java.my.lang;

public class Thread1 extends Thread {
    public static int index;
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        for (int a = 0; a < 2; a++) {
            new Thread1("dang" + a + ">>").start();
        }
    }

    @Override
    public void run() {
        index++;
        for (int a = 0; a < 5; a++) {
            System.out.println("Thread.name:" + name + a);
            try {
                sleep(200);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
        super.run();
    }
}
