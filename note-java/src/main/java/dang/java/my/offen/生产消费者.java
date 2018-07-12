package com.dang.java.my.offen;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by duang on 2017/3/12.
 */
public class 生产消费者 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 0, TimeUnit.DAYS.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
        Store store = new Store(10);
        Consumer consumer1 = new Consumer(store);
        // Consumer consumer2 = new Consumer(store);
        Producer producer1 = new Producer(store);
        Producer producer2 = new Producer(store);
        threadPoolExecutor.execute(producer1);
        threadPoolExecutor.execute(producer2);
        threadPoolExecutor.execute(consumer1);
    }
}

class Store {
    private final int MAX_SIZE;
    private int count;

    //private Queue<String> queue = new ArrayBlockingQueue<String>(MAX_SIZE);
    Store(int max_size) {
        MAX_SIZE = max_size;
        count = 0;
    }

    public synchronized void add() {
        while (count >= MAX_SIZE) {
            System.out.println("已经满了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().toString() + "put:" + count);
        this.notifyAll();
    }

    public synchronized void remove() {
        while (count <= 0) {
            System.out.println("空了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().toString() + "remove:" + count);
        this.notifyAll();
    }
}

class Producer extends Thread {
    private Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            store.add();
            try {
                Thread.sleep(10000 + new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            store.remove();
            try {
                Thread.sleep(10000 + new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
