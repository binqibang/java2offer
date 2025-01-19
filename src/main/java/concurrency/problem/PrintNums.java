package concurrency.problem;

public class PrintNums {
    private static final Object LOCK = new Object();
    private static int num;

    private static void print(int seq) {
        while (true) {
            synchronized (LOCK) {
                int max = 10;
                if (num % 3 != seq) {
                    if (num >= max) {
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                if (num >= max) {
                    break;
                }
                System.out.print(num++);
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> print(0)).start();
        new Thread(() -> print(1)).start();
        new Thread(() -> print(2)).start();
    }
}
