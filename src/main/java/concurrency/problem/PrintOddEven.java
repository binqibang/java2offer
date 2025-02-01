package concurrency.problem;

public class PrintOddEven {
    private static final Object LOCK = new Object();
    private static int count = 1;

    private static class Printer implements Runnable{
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + ": " + (count++));
                    LOCK.notify();
                    try {
                        if (count <= 100) {
                            LOCK.wait();
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadOdd = new Thread(new Printer(), "Odd");
        Thread threadEven = new Thread(new Printer(), "Even");
        threadOdd.start();
        Thread.sleep(100);
        threadEven.start();
    }
}
