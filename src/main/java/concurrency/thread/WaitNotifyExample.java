package concurrency.thread;

public class WaitNotifyExample {

    public synchronized void before() {
        System.out.println("--- before ---");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- after ---");
    }
}
