package com.dang.java.my.lang;

import java.util.concurrent.locks.ReentrantLock;

public class synchronized1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        t1.start();
        t2.start();
    }
}

/*
 *线程同步u
 */
class MyThread implements Runnable {
    //互斥锁
    private final ReentrantLock lock = new ReentrantLock();
    private int flag;

    @Override
    public void run() {
        eat();
    }

    private synchronized void eat() {
        //lock.lock();//上锁
        for (int a = 1; a < 5; a++) {
            flag = 0;
            System.out.println("开始打饭" + flag);
            System.out.println();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            flag = 1;
            System.out.println("饭已经打完" + flag);
            //--lock.unlock();//解锁
        }
    }

}
