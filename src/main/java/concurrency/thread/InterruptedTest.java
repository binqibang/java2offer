package concurrency.thread;

import static java.lang.Thread.interrupted;

public class InterruptedTest {
    public static void main(String[] args) {
        Runnable task = () -> {
            while (!interrupted()) {
                System.out.println("Thread is running");
            }
            System.out.println("Thread end");
        };
        Thread t = new Thread(task);
        t.start();
        t.interrupt();
    }
}
