package dang.jdk.thread;

import java.util.Random;

/**
 * Created by dangqihe on 2017/2/18.
 */
public class MaxThroead {
    public long count = 0;
    public Boolean alive = true;

    public static void main(String[] args) throws InterruptedException {
        MaxThroead maxThroead = new MaxThroead();
        int throeadCount = 100;
        while (true) {
            for (int i = 0; i < 100; i++) {
                Producer producer = new Producer(maxThroead);
                Thread thread = new Thread(producer);
                thread.start();
            }
            long start = System.currentTimeMillis();
            Random rand = new Random();
            Thread.sleep(1000);
            long end = System.currentTimeMillis();
            long avg = ((maxThroead.count) / (end - start));
            System.out.print("throeadCount:" + throeadCount + ":" + avg);
            for (int i = 0; i < (maxThroead.count / (throeadCount * 5)); i++) {
                System.out.print("*");
            }
            System.out.println(avg * throeadCount);
            if (throeadCount < 10000) {
                throeadCount += 100;
            } else {
                maxThroead.alive = false;
                return;
            }
            maxThroead.count = 0;
        }
    }

}

class Producer implements Runnable {
    MaxThroead maxThroead;

    public Producer(MaxThroead maxThroead) {
        this.maxThroead = maxThroead;
    }

    public void run() {
        while (maxThroead.alive) {
            work();
            maxThroead.count++;
        }
    }

    private void work() {
        double i = Math.pow(1.245, 456.2545);
        i = Math.pow(i, i);
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
