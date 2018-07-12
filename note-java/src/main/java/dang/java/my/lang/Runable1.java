package com.dang.java.my.lang;

public class Runable1 implements Runnable {
    private int ticket = 100;  //10张票

    public static void main(String[] args) {
        Runable1 my = new Runable1();
        Thread thread1 = new Thread(my, "1号窗口");
        Thread thread2 = new Thread(my, "2号窗口");
        Thread thread3 = new Thread(my, "3号窗口");
        thread1.setPriority(4);//设置优先级  主线程的优先级是5.
        thread2.setDaemon(true);//设置为守护线程 如果没有其他线程 守护线程会停止
        System.out.println("线程1是否运行" + thread1.isAlive());
        thread1.start();
        System.out.println("线程1是否运行" + thread1.isAlive());
        thread2.start();
        thread3.start();
        //thread1.interrupt();//中断线程 thread1
        System.out.println("---------end------------");
        try {
            thread3.join();//强制执行thread3   只用thread3执行完才能继续这个线程
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    public void run() {
        synchronized(this) {    //如果想解决这种问题，就需要使用同步。所谓同步就是在统一时间段中只有有一个线程运行，
            //的线程必须等到这个线程结束之后才能继续执行
            // public synchronized void sale()  同步方法u
            for (int i = 0; i <= 20; i++) {
                if (this.ticket > 0) {
                    if (i == 6) {
                        Thread.yield();//暂停当前线程 吧时间让给别的线程
                    }
                    System.out.println(Thread.currentThread().getName() + "正在卖票" + this.ticket--);
                    try {
                        Thread.sleep(1000);//线程的休眠
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "休眠被打断");
                        return;
                    }
                }
            }
        }
    }
}
/*
     实现Runnable接口比继承Thread类所具有的优势：
	1）：适合多个相同的程序代码的线程去处理同一个资源
	2）：可以避免java中的单继承的限制
	3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立。
	
	http://www.cnblogs.com/rollenholt/archive/2011/08/28/2156357.html
*/
