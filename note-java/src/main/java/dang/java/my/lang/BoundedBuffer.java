package com.dang.java.my.lang;

//生产消费者utf
public class BoundedBuffer {
    public static void main(String[] args) {
        Food food = new Food();
        Producter producter = new Producter(food);
        Customer customer = new Customer(food);
        Thread t1 = new Thread(producter);
        Thread t2 = new Thread(customer);
        t1.start();
        t2.start();
    }
}

//生产者
class Producter implements Runnable {
    private Food food;

    public Producter(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        // TODO 自动生成的方法存根
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                food.set("银耳莲子汤", "功效：美容养颜");
            } else {
                food.set("糖醋里脊", "功效：酸甜可口，可增肥");
            }
        }
    }

}

//消费者
class Customer implements Runnable {
    private Food food;

    public Customer(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        // TODO 自动生成的方法存根
        for (int i = 0; i < 50; i++) {
            food.get();
        }
    }

}

//消费的数据
class Food {
    public String name;
    public String efficasy;
    public boolean flag = true;//true表示可以生产  false表示可以消费

    //生产产品
    public synchronized void set(String name, String efficasy) {
        if (flag != true) {
            try {
                this.wait();    //当前进程进入无限期等待 让出cpu并释放锁  sleep不会释放锁
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        this.name = name;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        this.efficasy = efficasy;
        flag = false;//表示不能再生产  能取
        this.notify();//唤醒该监视器上的一个其他线程
    }

    //消费产品
    public synchronized void get() {
        if (flag != false) {
            try {
                this.wait();    //当前进程进入无限期等待 让出cpu并释放锁  sleep不会释放锁
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        System.out.println(name + efficasy);
        flag = true;//表示不能再取  能生产
        this.notify();//唤醒该监视器上的一个其他线程
    }

}
