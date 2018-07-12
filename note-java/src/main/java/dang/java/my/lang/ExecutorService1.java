package com.dang.java.my.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Java通过Executors提供四种线程池，分别为：
newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。u
newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
*/
public class ExecutorService1 {
    public static void main(String[] args) {
        int type = 3;
        System.out.println(type);
        ExecutorService es = null;
        switch (type) {
            case 1:
                //单线程线程池 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
                es = Executors.newSingleThreadExecutor();
                break;
            case 2:
                //定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
                es = Executors.newFixedThreadPool(2);
                break;
            case 3:
                //创建一个可缓存线程的执行器 （60秒空间将被收回）如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
                es = Executors.newCachedThreadPool();
                break;
            case 4:
                //创建一个无限大小的线程池  创建一个定长线程池，支持定时及周期性任务执行。
                es = Executors.newScheduledThreadPool(5);
                break;
            default:
                break;
        }
        es.execute(new DownloadThread());
        es.execute(new DownloadThread());
        es.execute(new DownloadThread());
        es.execute(new DownloadThread());

    }
}

class DownloadThread implements Runnable {
    public void run() {
        // TODO 自动生成的方法存根
        for (int a = 0; a <= 10; a++) {
            System.out.println(Thread.currentThread().getName() + "已加载" + a * 10 + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

}
