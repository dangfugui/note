package com.dang.java.my.lang;

/**
 * Created by dangqihe on 2017/3/3.
 */
public class 守护线程 {
    public static void main(String args[]) throws InterruptedException {
        Runnable textRunable = new TextRunable("main>textRunable");
        Thread thread = new Thread(textRunable);
        //守护线程中的线程 默认为守护线程
        thread.setDaemon(true);//设置为守护线程   当前线程开的线程结束后  开的线程也会结束
        thread.start();
        Thread.sleep(5000);
        System.out.println("end");
    }

}

class TextRunable implements Runnable {
    private String name;

    public TextRunable(String name) {
        this.name = name;
    }

    public void run() {
        if (name.contains("main")) {
            Runnable textRunable = new TextRunable(">textRunable>textRunable");
            Thread thread = new Thread(textRunable);
            // thread.setDaemon(false);//当守护线程中有非守护线程  守护线程变成非守护
            thread.start();
        }
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ">run");
        }
    }
}
