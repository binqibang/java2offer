package concurrency.problem;

import java.util.concurrent.CountDownLatch;

public class CAfterAB {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " finish.");
            cdl.countDown();
        }, "Thread-A");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " finish.");
            cdl.countDown();
        }, "Thread-B");

        Thread t3 = new Thread(() -> {
            try {
                cdl.await();
                System.out.println(Thread.currentThread().getName() + " start.");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " finish.");
        }, "Thread-C");

        t3.start();
        t2.start();
        t1.start();
    }
}
